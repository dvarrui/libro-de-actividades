/* Sound playback with ALSA. */

#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/asoundlib.h>

/* Plays a sound with the Advanced Linux Sound Architecture, ALSA.
   Returns 0 on successful playback, nonzero on error. */
int PlayerALSA(u_int8_t *samples, unsigned int bits, unsigned int channels,
               unsigned int rate, unsigned int bytes)
{
    int i;

    /* ALSA is a bit verbose, and it tends to require
       lots of structures. */
    unsigned int alsa_device = 0, alsa_card = 0;
    char *alsa_card_name;
    snd_ctl_t *alsa_ctl;
    snd_ctl_hw_info_t alsa_hw_info;
    snd_pcm_t *alsa_pcm;
    snd_pcm_channel_params_t alsa_params;
	
    /* Playback status variables. */
    unsigned int position;

    /* Scan for ALSA cards and devices. Each card has an integer ID
       less than snd_cards(). We scan for the first available card
       in order to demonstrate ALSA's organization, but we could
       find the default card and PCM device numbers immediately with
       the snd_defaults_pcm_card(), snd_defaults_pcm_device() functions. */
    alsa_pcm = NULL;
    for (alsa_card = 0; alsa_card < (unsigned)snd_cards(); alsa_card++) {

	/* Try to open this card. */
	if (snd_ctl_open(&alsa_ctl,alsa_card) < 0)
	    continue;
		
	/* Retrieve card info. */
	if (snd_ctl_hw_info(alsa_ctl,&alsa_hw_info) < 0) {
	    snd_ctl_close(alsa_ctl);
	    continue;
	}
		
	snd_ctl_close(alsa_ctl);
		
	/* Find a suitable device on this card. */
	alsa_pcm = NULL;
	for (alsa_device = 0; alsa_device < alsa_hw_info.pcmdevs; 
	     alsa_device++) {
	    if (snd_pcm_open(&alsa_pcm,alsa_card,
			     alsa_device,SND_PCM_OPEN_PLAYBACK) < 0) continue;
	    
	    /* Device successfully opened. */
	    break;
	}
		
	if (alsa_pcm != NULL) break;
    }
	
    /* Were we able to open a device? */
    if (alsa_card == (unsigned)snd_cards()) {
	printf("ALSA player: unable to find a configured device.\n");
	return -1;
    }
	
    /* Print info about the device. */
    if (snd_card_get_longname(alsa_card,&alsa_card_name) < 0)
	alsa_card_name = "(unknown)";
    printf("ALSA player: using device %i:%i (%s)\n",
	   alsa_card, alsa_device, alsa_card_name);
	
    /* Configure the device for the loaded sound data. */
    memset(&alsa_params,0,sizeof (alsa_params));
    alsa_params.channel = SND_PCM_CHANNEL_PLAYBACK;

    /* Use stream mode. In this mode, we don't have to give ALSA complete
       blocks; we can send it data as we get it. Block mode is needed for
       mmap() functionality. Unlike OSS, ALSA's mmap() functionality is
       quite reliable, and easily accessible through library functions.
       We won't use it here, though; there's no need. */
    alsa_params.mode = SND_PCM_MODE_STREAM;
    alsa_params.format.interleave = 1;
    
    /* We'll assume little endian samples. You may wish to use the data in
       the GNU C Library's endian.h to support other endiannesses. We're
       ignoring that case for simplicity. */
    if (bits == 8)
	alsa_params.format.format = SND_PCM_SFMT_U8;
    else if (bits == 16)
	alsa_params.format.format = SND_PCM_SFMT_S16_LE;
    else {
	printf("ALSA player: invalid sample size.\n");
	return -1;
    }
    alsa_params.format.rate = rate;
    alsa_params.format.voices = channels;
    alsa_params.start_mode = SND_PCM_START_DATA;
    alsa_params.stop_mode = SND_PCM_STOP_ROLLOVER;
    alsa_params.buf.block.frag_size = 4096;
    alsa_params.buf.block.frags_min = 1;
    alsa_params.buf.block.frags_max = 2;
	
    if ((i = snd_pcm_plugin_params(alsa_pcm,&alsa_params)) < 0) {
	printf("ALSA player: unable to set parameters.\n");
	snd_pcm_close(alsa_pcm);
	return -1;
    }

    if (snd_pcm_plugin_prepare(alsa_pcm, SND_PCM_CHANNEL_PLAYBACK) < 0) {
	printf("ALSA player: unable to prepare playback.\n");
	snd_pcm_close(alsa_pcm);
	return -1;
    }
	
    /* Feed the sound data to ALSA. */
    position = 0;
    while (position < bytes) {
	int written, blocksize;
		
	if (bytes-position < 4096)
	    blocksize = bytes-position;
	else
	    blocksize = 4096;
		
	/* Write to the sound device. */
	written = snd_pcm_plugin_write(alsa_pcm, &samples[position], blocksize);

	/* If ALSA can't take any more data right now, it'll return -EAGAIN.
	   If this were sound code for a game, we'd probably just contine the
	   game loop and try to write data the next time around.
	   In a game, you'd probably also want to put the device in nonblocking
	   mode (see the snd_pcm_nonblock_mode() function). */
	if (written == -EAGAIN) {
	    /* Waste some time. This keeps us from using 100% CPU. */
	    usleep(1000);

	    written = 0;
	} else {
	    if (written < 0) {
		perror("\nALSA player: error writing to sound device");
		snd_pcm_close(alsa_pcm);
		return -1;
	    }
	}

	/* Update the position. */
	position += written;

	/* Print some information. */
	WritePlaybackStatus(position, bytes, channels, bits, rate);
	printf("\r");
	fflush(stdout);
    }

    printf("\n");

    /* Wait until ALSA's internal buffers are empty, then stop playback.
       This will make sure that the entire sound clip has played. */
    snd_pcm_channel_flush(alsa_pcm, SND_PCM_CHANNEL_PLAYBACK);
    snd_pcm_close(alsa_pcm);

    return 0;
}
