CFLAGS=-W -Wall -ggdb -O2
CC=gcc

.PHONY: default clean

default: keycodes keynames modeswitch mouse simplefb

clean:
	rm -f keycodes keynames modeswitch mouse simplefb
	rm -f *~

keycodes: keycodes.c
	$(CC) $(CFLAGS) keycodes.c -o keycodes

keynames: keynames.c
	$(CC) $(CFLAGS) keynames.c -o keynames

modeswitch: modeswitch.c fbmodedb.c
	$(CC) $(CFLAGS) modeswitch.c fbmodedb.c -o modeswitch

mouse: mouse.c
	$(CC) $(CFLAGS) mouse.c -o mouse -lgpm

simplefb: simplefb.c
	$(CC) $(CFLAGS) simplefb.c -o simplefb
