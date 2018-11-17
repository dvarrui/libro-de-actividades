CFLAGS=-W -Wall -ggdb `sdl-config --cflags`

# There are quite a few libs here.
# You should check that you have all of them.
LIBS=-lm `sdl-config --libs` -lopenal -lsndfile -lvorbisfile -lvorbis -logg

OBJS=main.o particle.o background.o resources.o audio.o music.o
CC=gcc


.PHONY: clean default

default:
	@echo 'Plese check the variables at the top of the Makefile, then run make pw.'

clean:
	rm -f pw $(OBJS)
	rm -f *~

pw: $(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o pw $(LIBS)

main.o: main.c gamedefs.h
	$(CC) $(CFLAGS) -c main.c

particle.o: particle.c particle.h gamedefs.h
	$(CC) $(CFLAGS) -c particle.c

background.o: background.c background.h gamedefs.h
	$(CC) $(CFLAGS) -c background.c

resources.o: resources.c resources.h gamedefs.h
	$(CC) $(CFLAGS) -c resources.c

audio.o: audio.c audio.h resources.h gamedefs.h
	$(CC) $(CFLAGS) -c audio.c

music.o: music.c music.h resources.h gamedefs.h
	$(CC) $(CFLAGS) -c music.c
