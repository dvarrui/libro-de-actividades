/* Sound playback with ESD. */

#include <unistd.h>
#include <stdio.h>
#include <esd.h>

/* Plays a sound with the Enlightened Sound Daemon (ESD).
   Returns 0 on successful playback, nonzero on error. */
int PlayerESD(u_int8_t *samples, unsigned int bits, unsigned int channels,
              unsigned int rate, unsigned int bytes)
{
    /* ESD data socket. */
    int esd = 0;
	
    int esd_flags;
	
    /* Playback status variables. */
    unsigned int position;
	
    /* Select the appropriate ESD flags. */
    switch (channels) {
    case 1: esd_flags = ESD_MONO; break;
    case 2: esd_flags = ESD_STEREO; break;		
    default:
	printf("ESD player: unknown number of channels.\n");
	return -1;
    }
	
    switch (bits) {
	/* ESD sometimes has problems with 8-bit sound. */
    case 8: esd_flags |= ESD_BITS8; break;
    case 16: esd_flags |= ESD_BITS16; break;
    default:
	printf("ESD player: unknown sample size.\n");
	return -1;
    }

    /* Open ESD with the desired parameters. */
    esd = esd_play_stream(esd_flags | ESD_PLAY | ESD_STREAM, rate,
			  NULL ,"PlayerESD");	
			      
    if (esd < 0) {
	printf("ESD player: unable to connect to ESD.\n");
	return -1;
    }
	
    /* Feed the sound data to ESD. */
    position = 0;
    while (position < bytes) {
	int written, blocksize;
		
	/* ESD has a fixed buffer size. */
	if (bytes-position < ESD_BUF_SIZE)
	    blocksize = bytes-position;
	else
	    blocksize = ESD_BUF_SIZE;
		
	/* Write to the sound device. */
	written = write(esd, &samples[position], blocksize);
	if (written == -1) {
	    perror("\nESD player: error writing to sound device");
	    close(esd);
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
    close(esd);

    return 0;
}
