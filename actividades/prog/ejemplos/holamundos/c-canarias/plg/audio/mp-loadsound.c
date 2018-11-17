/* Sound loader for Multi-Play. */

#include <stdio.h>
#include <stdlib.h>
#include <sndfile.h>

/* Loads a sound file from disk into a newly allocated buffer.
   Sets *rate to the sample rate of the sound, *channels to the
   number of channels (1 for mono, 2 for stereo), *bits to the sample
   size in bits (8 or 16), *buf to the address of the buffer, and
   *buflen to the length of the buffer in bytes. 16-bit samples will
   be stored using the host machine's endianness (little endian on
   Intel-based machines, big endian on PowerPC, etc.)
   
   8-bit samples will always be unsigned. 16-bit will always be signed.
   Channels are interleaved.
   
   Requires libsndfile to be linked into the program.
   
   Returns 0 on success and nonzero on failure. Prints an error
   message on failure. */
   
int LoadSoundFile(char *filename, unsigned int *rate, unsigned int *channels,
                  unsigned int *bits, u_int8_t **buf, unsigned int *buflen)
{
    SNDFILE *file;
    SF_INFO file_info;
    short *buffer_short = NULL;
    u_int8_t *buffer_8 = NULL;
    int16_t *buffer_16 = NULL;
    unsigned int i;
	
    /* Open the file and retrieve sample information. */
    file = sf_open_read(filename, &file_info);
    if (file == NULL) {
	printf("Unable to open '%s'.\n", filename);
	return -1;
    }
	
    /* Make sure the format is acceptable. */
    if ((file_info.format & 0x0F) != SF_FORMAT_PCM) {
	printf("'%s' is not a PCM-based audio file.\n", filename);
	sf_close(file);
	return -1;
    }
	
    if ((file_info.pcmbitwidth != 8) && (file_info.pcmbitwidth != 16)) {
	printf("'%s' uses an unrecognized sample size.\n", filename);
	sf_close(file);
	return -1;
    }
	
    /* Allocate buffers. */
    buffer_short = (short *)malloc(file_info.samples *
				   file_info.channels * 
				   sizeof (short));

    buffer_8 = (u_int8_t *)malloc(file_info.samples *
				  file_info.channels *
				  file_info.pcmbitwidth / 8);

    buffer_16 = (int16_t *)buffer_8;

    if (buffer_short == NULL || buffer_8 == NULL) {
	printf("Unable to allocate enough memory for '%s'.\n", filename);
	fclose(file);
	free(buffer_short);
	free(buffer_8);
	return -1;
    }

    /* Read the entire sound file. */
    if (sf_readf_short(file,buffer_short,file_info.samples) == (size_t)-1) {
	printf("Error while reading samples from '%s'.\n", filename);
	fclose(file);
	free(buffer_short);
	free(buffer_8);
	return -1;
    }
	
    /* Convert the data to the correct format. */
    for (i = 0; i < file_info.samples * file_info.channels; i++) {
	if (file_info.pcmbitwidth == 8) {
	    /* Convert the sample from a signed short to an unsigned byte */
	    buffer_8[i] = (u_int8_t)((short)buffer_short[i] + 128);
	} else {
	    buffer_16[i] = (int16_t)buffer_short[i];
	}
    }
	
    /* Return the sound data. */
    *rate = file_info.samplerate;
    *channels = file_info.channels;
    *bits = file_info.pcmbitwidth;
    *buf = buffer_8;
    *buflen = file_info.samples * file_info.channels * file_info.pcmbitwidth / 8;
	
    /* Close the file and return success. */
    sf_close(file);
    free(buffer_short);

    return 0;
}
