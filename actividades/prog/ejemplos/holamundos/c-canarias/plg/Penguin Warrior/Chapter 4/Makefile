CFLAGS=-W -Wall -ggdb `sdl-config --cflags`
LIBS=-lm `sdl-config --libs`
OBJS=main.o particle.o background.o resources.o
CC=gcc

.PHONY: clean default

default:
	@echo 'Plese check the variables at the top of the Makefile, then run make pw.'

clean:
	rm -f pw $(OBJS)
	rm -f *~

pw:	$(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o pw $(LIBS)

main.o:	 main.c gamedefs.h
	$(CC) $(CFLAGS) -c main.c

particle.o: particle.c particle.h gamedefs.h
	$(CC) $(CFLAGS) -c particle.c

background.o: background.c background.h gamedefs.h
	$(CC) $(CFLAGS) -c background.c

resources.o: resources.c resources.h gamedefs.h
	$(CC) $(CFLAGS) -c resources.c
