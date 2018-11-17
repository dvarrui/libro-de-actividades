/* DMA sound playback with OSS. */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <sys/soundcard.h>
#include <sys/mman.h>

/* Plays a sound with OSS (/dev/dsp), using direct DMA access.
   Returns 0 on successful playback, nonzero on error. */
int PlayerDMA(u_int8_t *samples, unsigned int bits, unsigned int channels,
              unsigned int rate, unsigned int bytes)
{
    /* file handle for /dev/dsp */
    int dsp = 0;

    /* Variables for ioctl's. */
    unsigned int requested, ioctl_format, ioctl_channels,
	ioctl_rate, ioctl_caps, ioctl_enable;
    audio_buf_info ioctl_info;
	
    /* Buffer information. */
    unsigned int frag_count, frag_size;
    u_int8_t *dmabuffer = NULL;
    unsigned int dmabuffer_size = 0;
    int dmabuffer_flag = 0;

    /* Playback status variables. */
    unsigned int position = 0, done = 0;

    /* Attempt to open /dev/dsp for playback. We need to open for
       read/write in order to mmap() the device file. */
    dsp = open("/dev/dsp",O_RDWR);
	
    /* This could very easily fail, so we must handle errors. */
    if (dsp < 0) {
	perror("DMA player: error opening /dev/dsp for playback");
	goto error;
    }
	
    /* Select the appropriate sample format. */
    switch (bits) {
    case 8: ioctl_format = AFMT_U8; break;		
    case 16: ioctl_format = AFMT_S16_NE; break;		
    default: printf("DMA player: unknown sample size.\n");
	goto error;
    }

    /* We've decided on a format. We now need to pass it to OSS. */
    requested = ioctl_format;
    if (ioctl(dsp,SNDCTL_DSP_SETFMT,&ioctl_format) == -1) {
	perror("DMA player: format selection failed");
	goto error;
    }
	
    /* ioctl's usually modify their arguments. SNDCTL_DSP_SETFMT
       sets its integer argument to the sample format that OSS
       actually gave us. This could be different than what we
       requested. For simplicity, we will not handle this situation. */
    if (requested != ioctl_format) {
	printf("DMA player: unsupported sample format.\n");
	goto error;
    }
		
    /* We must inform OSS of the number of channels (mono or stereo)
       before we set the sample rate. This is due to limitations in
       some (older) sound cards. */
    ioctl_channels = channels;
    if (ioctl(dsp,SNDCTL_DSP_CHANNELS,&ioctl_channels) == -1) {
	perror("DMA player: unable to set the number of channels");
	goto error;
    }

    /* OSS might not have granted our request, even if the ioctl
       succeeded. */
    if (channels != ioctl_channels) {
	printf("DMA player: unable to set the number of channels.\n");
	goto error;
    }

    /* We can now set the sample rate. */
    ioctl_rate = rate;
    if (ioctl(dsp,SNDCTL_DSP_SPEED,&ioctl_rate) == -1) {
	perror("DMA player: unable to set sample rate");
	goto error;
    }
	
    /* OSS sets the SNDCTL_DSP_SPEED argument to the actual sample rate,
       which may be different from the requested rate. In this case, a
       production-quality player would upsample or downsample the sound
       data. We'll simply report an error. */
    if (rate != ioctl_rate) {
	printf("DMA player: unable to set the desired sample rate.\n");
	goto error;
    }

    /* Now check for DMA compatibility. It's quite possible that the driver
       won't support this. It would be a *very* good idea to provide a
       fallback in case DMA isn't supported - there are some sound cards that
       simply don't work with the DMA programming model at all. */
    if (ioctl(dsp,SNDCTL_DSP_GETCAPS,&ioctl_caps) != 0) {
	perror("DMA player: unable to read sound driver capabilities");
	goto error;
    }
	
    /* The MMAP and TRIGGER bits must be set for this to work.
       MMAP gives us the ability to access the DMA buffer directly,
       and TRIGGER gives us the ability to start the sound card's
       playback with a special ioctl. */
    if (!(ioctl_caps & DSP_CAP_MMAP) || !(ioctl_caps & DSP_CAP_TRIGGER)) {
	printf("DMA player: this sound driver is not capable of direct DMA.");
	goto error;
    }

    /* Query the sound driver for the actual fragment configuration
	   so that we can calculate the total size of the DMA buffer.
	   Note that we haven't selected a particular fragment size or
	   count. Fragment boundaries are meaningless in a mapped buffer;
	   we're really just interested in the total size. */
    if (ioctl(dsp,SNDCTL_DSP_GETOSPACE,&ioctl_info) != 0) {
	perror("DMA player: unable to query buffer information");
	goto error;
    }

    frag_count = ioctl_info.fragstotal;
    frag_size = ioctl_info.fragsize;
    dmabuffer_size = frag_count * frag_size;
	
    /* We're good to go. Map a buffer onto the audio device. */
    dmabuffer = mmap(NULL,
		     dmabuffer_size,         /* length of region to map */
		     PROT_WRITE,             /* select the output buffer
						(PROT_READ alone selects input) */
		     /* NOTE: I had to add PROT_READ to
			make this work with FreeBSD.
			However, this causes the code to
			fail under Linux. SNAFU. */
		     MAP_FILE | MAP_SHARED,	 /* see the mmap() manual page */
		     dsp,			 /* opened file to map */
		     0);			 /* start at offset zero */

    /* This could fail for a number of reasons. */
    if (dmabuffer == (u_int8_t *)MAP_FAILED) {
	perror("DMA player: unable to mmap a DMA buffer");
	goto error;
    }
	
    /* Clear the buffer to avoid static at the beginning. */
    memset(dmabuffer, 0, dmabuffer_size);

    /* The DMA buffer is ready! Now we can start playback by toggling
       the device's PCM output bit. Yes, this is a very hacky interface.
       We're actually using the OSS "trigger" functionality here. */
    ioctl_enable = 0;
    if (ioctl(dsp, SNDCTL_DSP_SETTRIGGER, &ioctl_enable) != 0) {
	perror("DMA player: unable to disable PCM output");
	goto error;
    }
	
    ioctl_enable = PCM_ENABLE_OUTPUT;
    if (ioctl(dsp, SNDCTL_DSP_SETTRIGGER, &ioctl_enable) != 0) {
	perror("DMA player: unable to enable PCM output");
	goto error;
    }

    /* The done variable simply makes sure that the last chunk actually
       gets played. We'll play a brief period of silence after the last
       data chunk. */
    while (done < 4) {
	struct count_info status;
	unsigned int i;
		
	/* Find the location of the DMA controller within the buffer.
	   This will be exact at least to the level of a fragment. */
	if (ioctl(dsp, SNDCTL_DSP_GETOPTR, &status) != 0) {
	    perror("DMA player: unable to query playback status");
	    goto error;
	}
		
	/* Our buffer is comprised of several fragments. However, in DMA
	   mode, it is safe to treat the entire buffer as one big block.
	   We will divide it into two logical chunks. While the first chunk
	   is playing, we will fill the second with new samples, and 
	   vice versa. With a small buffer, we will still enjoy low latency.
		   
	   status.ptr contains the offset of the DMA controller within
	   the buffer. */

	if (dmabuffer_flag == 0) {
	    /* Do we need to refill the first chunk? */
	    if ((unsigned)status.ptr < dmabuffer_size/2) {
		unsigned int amount;
		
		/* Copy data into the DMA buffer. */
		if (bytes - position < dmabuffer_size/2) {
		    amount = bytes-position;
		} else amount = dmabuffer_size/2;
		
		for (i = 0; i < amount; i++) {
		    dmabuffer[i+dmabuffer_size/2] = samples[position+i];
		}
		
		/* Zero the rest of this half. */
		for (; i < dmabuffer_size/2; i++) {
		    dmabuffer[i+dmabuffer_size/2] = 0;
		}
								
		/* Update the buffer position. */
		position += amount;
			
		/* Next update will be the first chunk. */
		dmabuffer_flag = 1;
				
		/* Have we reached the end? */
		if (position >= bytes) done++;
	    }
	} else if (dmabuffer_flag == 1) {
	    /* Do we need to refill the first chunk? */
	    if ((unsigned)status.ptr >= dmabuffer_size/2) {
		unsigned int amount;

		/* Copy data into the DMA buffer. */
		if (bytes - position < dmabuffer_size/2) {
		    amount = bytes-position;
		} else amount = dmabuffer_size/2;
				
		for (i = 0; i < amount; i++) {
		    dmabuffer[i] = samples[position+i];
		}

		/* Zero the rest of this half. */
		for (; i < dmabuffer_size/2; i++) {
		    dmabuffer[i] = 0;
		}
				
		/* Update the buffer position. */
		position += amount;
							
		/* Next update will be the second chunk. */
		dmabuffer_flag = 0;

		/* Have we reached the end? */
		if (position >= bytes) done++;
	    }
	}

	WritePlaybackStatus(position, bytes, channels, bits, rate); 
	printf(" (%i)\r", dmabuffer_flag);
	fflush(stdout);
		
	/* Wait a while. A game would normally do the rest of its
	   processing here. */
	usleep(50);
    }

    printf("\n");

    munmap(dmabuffer,dmabuffer_size);
    close(dsp);

    return 0;
	
    /* Error handler. goto's are normally bad, but they make sense here. */
 error:
    if (dmabuffer != NULL)
	munmap(dmabuffer,dmabuffer_size);
    if (dsp > 0) close(dsp);
		
    return -1;
}
