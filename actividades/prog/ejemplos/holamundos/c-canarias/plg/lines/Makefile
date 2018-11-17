CFLAGS=-W -Wall -ggdb -O2 `sdl-config --cflags`
CC=gcc

.PHONY: default clean

default: bresline lineclip

clean:
	rm -f bresline lineclip
	rm -f *~

bresline: bresline.c
	$(CC) $(CFLAGS) bresline.c -o bresline `sdl-config --libs`

lineclip: lineclip.c
	$(CC) $(CFLAGS) lineclip.c -o lineclip `sdl-config --libs`
