#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <sys/soundcard.h>
#include <sys/mman.h>

/* Plays a sound with OSS (/dev/dsp), with less latency.
   Returns 0 on successful playback, nonzero on error. */
int PlayerOSS2(u_int8_t *samples, unsigned int bits, unsigned int channels,
               unsigned int rate, unsigned int bytes)
{
    /* file handle for /dev/dsp */
    int dsp = 0;

	/* Variables for ioctl's. */
    unsigned int requested, ioctl_format, ioctl_channels,
	ioctl_rate, ioctl_frag;
	
    /* Playback status variables. */
    unsigned int position;


    /* Attempt to open /dev/dsp for playback (writing). */
    dsp = open("/dev/dsp",O_WRONLY);
	
    /* This could very easily fail, so we must handle errors. */
    if (dsp == -1) {
	perror("OSS player 2: error opening /dev/dsp for playback");
	return -1;
    }
	
    /* Set the fragment parameters. See the text for an explanation. */
    ioctl_frag = 10;		/* fragment size is 2^10 = 1024 bytes */
    ioctl_frag += 3 * 65536;	/* fragment count is 3 */
    if (ioctl(dsp,SNDCTL_DSP_SETFRAGMENT,&ioctl_frag) != 0) {
	perror("OSS player 2: fragment configuration failed");
	close(dsp);
	return 1;
    }

    /* Select the appropriate sample format. */
    switch (bits) {
    case 8: ioctl_format = AFMT_U8; break;		
    case 16: ioctl_format = AFMT_S16_NE; break;		
    default: printf("OSS player 2: unknown sample size.\n"); return -1;
    }

    /* We've decided on a format. We now need to pass it to OSS.
	   ioctl is a very generalized interface. We always pass data
	   to it by reference, not by value, even if the data is a
	   simple integer. */
    requested = ioctl_format;
    if (ioctl(dsp,SNDCTL_DSP_SETFMT,&ioctl_format) == -1) {
	perror("OSS player 2: format selection failed");
	close(dsp);
	return -1;
    }
	
    /* ioctl's usually modify their arguments. SNDCTL_DSP_SETFMT
	   sets its integer argument to the sample format that OSS
	   actually gave us. This could be different than what we
	   requested. For simplicity, we will not handle this situation. */
    if (requested != ioctl_format) {
	printf("OSS player 2: unsupported sample format.\n");
	close(dsp);
	return -1;
    }
		
    /* We must inform OSS of the number of channels (mono or stereo)
	   before we set the sample rate. This is due to limitations in
	   some (older) sound cards. */
    ioctl_channels = channels;
    if (ioctl(dsp,SNDCTL_DSP_CHANNELS,&ioctl_channels) == -1) {
	perror("OSS player 2: unable to set the number of channels");
	close(dsp);
	return -1;
    }

    /* OSS might not have granted our request, even if the ioctl
	   succeeded. */
    if (channels != ioctl_channels) {
	printf("OSS player 2: unable to set the number of channels.\n");
	close(dsp);
	return -1;
    }

    /* We can now set the sample rate. */
    ioctl_rate = rate;
    if (ioctl(dsp,SNDCTL_DSP_SPEED,&ioctl_rate) == -1) {
	perror("OSS player 2: unable to set sample rate");
	close(dsp);
	return -1;
    }
	
    /* OSS sets the SNDCTL_DSP_SPEED argument to the actual sample rate,
	   which may be different from the requested rate. In this case, a
	   production-quality player would upsample or downsample the sound
	   data. We'll simply report an error. */
    if (rate != ioctl_rate) {
	printf("OSS player 2: unable to set the desired sample rate.\n");
	close(dsp);
	return -1;
    }

    /* Feed the sound data to OSS. */
    position = 0;
    while (position < bytes) {
	int written, blocksize;
		
	/* We should write data in increments of the fragment
	   size, if possible. */
	if (bytes-position < 1024)
	    blocksize = bytes-position;
	else
	    blocksize = 1024;
		
	/* Wait until a fragment is available. */
	for (;;) {
	    audio_buf_info info;
			
	    /* Ask OSS if there is any free space in the buffer. */
	    if (ioctl(dsp,SNDCTL_DSP_GETOSPACE,&info) != 0) {
		perror("Unable to query buffer space");
		close(dsp);
		return 1;
	    }

	    /* Any empty fragments? */
	    if (info.fragments > 0) break;
						
	    /* Not enough free space in the buffer.
	       Waste time. */
	    usleep(100);
	}

	/* Write to the sound device. */
	written = write(dsp,&samples[position],blocksize);
	if (written == -1) {
	    perror("\nOSS player 2: error writing to sound device");
	    close(dsp);
	    return -1;
	}

	/* Update the position. */
	position += written;

	/* Print some information. */
	WritePlaybackStatus(position, bytes, channels, bits, rate);
	printf("\r");
	fflush(stdout);
    }

    printf("\n");
    close(dsp);

    return 0;
}
