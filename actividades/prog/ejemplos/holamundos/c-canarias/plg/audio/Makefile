CFLAGS=-W -Wall -ggdb -O2
CC=gcc
SNDFILE_LIB=-lsndfile

# To disable ALSA support, add -DDISABLE_ALSA.
# To disable ESD support, add -DDISABLE_ESD.
# To disable OSS support, add -DDISABLE_OSS.
# Also edit the LIBS line to remove unnecessary libraries.

DISABLE=
LIBS=-lesd -lasound


.PHONY: default clean

default:
	@echo 'Please edit the Makefile and configure the DISABLE and LIBS lines.'
	@echo 'Then run make multi-play to build the program.'

clean:
	rm -f multi-play
	rm -f *~


multi-play: mp-alsa.c mp-esd.c mp-oss.c mp-oss2.c mp-dma.c mp-loadsound.c multi-play.c
	$(CC) $(CFLAGS) $(DISABLE) multi-play.c -o multi-play $(LIBS) $(SNDFILE_LIB)
