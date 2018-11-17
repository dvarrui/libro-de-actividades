/* Multi-Play's main file. */

/* Selectively enable compilation of parts of the player. */
#ifndef DISABLE_OSS
#define ENABLE_OSS
#endif

#ifndef DISABLE_ESD
#define ENABLE_ESD
#endif

#ifndef DISABLE_ALSA
#define ENABLE_ALSA
#endif

#include <stdio.h>
#include <sndfile.h>
#include <endian.h>

/* sys/types.h provides convenient typedefs, such as int8_t. */
#include <sys/types.h>

/* ESD header. */
#ifdef ENABLE_ESD
#include <esd.h>
#endif

/* ALSA header. */
#ifdef ENABLE_ALSA
#include <sys/asoundlib.h>
#endif

/* Prototypes. */
void WritePlaybackStatus(unsigned int position, unsigned int total, unsigned int channels,
                         unsigned int bits, unsigned int rate);
int LoadSoundFile(char *filename, unsigned int *rate, unsigned int *channels,
                  unsigned int *bits, u_int8_t **buf, unsigned int *buflen);		  

int PlayerOSS(u_int8_t *samples, unsigned int bits, unsigned int channels, unsigned int rate, unsigned int bytes);
int PlayerOSS2(u_int8_t *samples, unsigned int bits, unsigned int channels, unsigned int rate, unsigned int bytes);
int PlayerDMA(u_int8_t *samples, unsigned int bits, unsigned int channels, unsigned int rate, unsigned int bytes);
int PlayerESD(u_int8_t *samples, unsigned int bits, unsigned int channels, unsigned int rate, unsigned int bytes);
int PlayerALSA(u_int8_t *samples, unsigned int bits, unsigned int channels, unsigned int rate, unsigned int bytes);

/* The loader code is in a separate file for book organization. */
#include "mp-loadsound.c"

/* Optionally include the OSS player code. */
#ifdef ENABLE_OSS
# include "mp-oss.c"
# include "mp-oss2.c"
# include "mp-dma.c"
#endif

/* Optionally include the ESD player code. */
#ifdef ENABLE_ESD
# include "mp-esd.c"
#endif

/* Optionally include the ALSA player code. */
#ifdef ENABLE_ALSA
# include "mp-alsa.c"
#endif

/* Writes a playback status line, with no \n. This is purely for aesthetic value. */
void WritePlaybackStatus(unsigned int position, unsigned int total,
                         unsigned int channels, unsigned int bits, unsigned int rate)
{
    unsigned int i;
	
    printf("[");
    for (i = 0; i < (10*position/total); i++)
	printf("-");
    printf("|");
    for (; i < 10; i++)
	printf("-");
    printf("] %3i%% ", (100*position/total));
    printf("%2i-bit %s @ %i KHz",
	   bits,
	   (channels == 1 ? "mono  " : "stereo"),
	   rate/1000);
}

/* Prints a summary of command-line usage. */
void usage(char *progname)
{
    printf("Usage: %s player filenames\n", progname);
    printf("  Available players are:\n");
#ifdef ENABLE_OSS
    printf("  --oss     Normal OSS output, somewhat latent.\n");
    printf("  --oss2    Normal OSS output, less latency.\n");
    printf("  --dma     Direct DMA access with OSS.\n");
#endif
#ifdef ENABLE_ESD
    printf("  --esd     Output to ESD.\n");
#endif
#ifdef ENABLE_ALSA
    printf("  --alsa    Normal ALSA output.\n");
#endif
}

/* The main player program. */
int main(int argc, char *argv[])
{
    char *filename;
    int arg;
    enum { OSS, OSS2, OSSDMA, ESD, ALSA } player;

    /* Variables for the loaded sound. */
    u_int8_t *samples = NULL;
    int sample_size;
    int sample_rate;
    int sample_bytes;
    int num_channels;	
	
    if (argc < 3) {
	usage(argv[0]);
	return 1;
    }

    /* Decide which player to use. */
    if (0) { }
#ifdef ENABLE_OSS
    else if (!strcmp(argv[1],"--oss"))
	player = OSS;
    else if (!strcmp(argv[1],"--oss2"))
	player = OSS2;
    else if (!strcmp(argv[1],"--dma"))
	player = OSSDMA;
#endif
#ifdef ENABLE_ESD
    else if (!strcmp(argv[1],"--esd"))
	player = ESD;
#endif
#ifdef ENABLE_ALSA
    else if (!strcmp(argv[1],"--alsa"))
	player = ALSA;
#endif		
    else {
	usage(argv[0]);
	return 1;
    }
	
    /* Treat the rest of the command line as filenames to play. */
    for (arg = 2; arg < argc; arg++) {
	filename = argv[arg];
	
	/* Load the sound data. */
	if (LoadSoundFile(filename, &sample_rate, &num_channels,
			  &sample_size, &samples, &sample_bytes) != 0) {
	    printf("Skipping '%s'.\n", filename);
	    continue;
	}
		
	switch (player) {
#ifdef ENABLE_OSS
	case OSS:
	    if (PlayerOSS(samples, sample_size, num_channels,
			  sample_rate, sample_bytes) != 0)
		printf("Sound playback with OSS failed.\n");
	    break;
			
	case OSS2:
	    if (PlayerOSS2(samples, sample_size, num_channels,
			   sample_rate, sample_bytes) != 0)
		printf("Sound playback with OSS2 failed.\n");
	    break;
			
	case OSSDMA:
	    if (PlayerDMA(samples, sample_size, num_channels,
			  sample_rate, sample_bytes) != 0)
		printf("Sound playback with DMA failed.\n");
	    break;
#endif
#ifdef ENABLE_ESD			
	case ESD:
	    if (PlayerESD(samples, sample_size, num_channels,
			  sample_rate, sample_bytes) != 0)
		printf("Sound playback with ESD failed.\n");
	    break;
#endif
#ifdef ENABLE_ALSA		
	case ALSA:
	    if (PlayerALSA(samples, sample_size, num_channels,
			   sample_rate, sample_bytes) != 0)
		printf("Sound playback with ALSA failed.\n");
	    break;
#endif
	default:
	    printf("Bug!\n");
	    return 1;
	}
		
	free(samples);
    }
	
    return 0;
	
}
