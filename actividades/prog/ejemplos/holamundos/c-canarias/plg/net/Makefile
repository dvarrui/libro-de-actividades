CFLAGS=-W -Wall -ggdb -O2
CC=gcc

.PHONY: all

all: tcpclient tcpserver udpsender udpreceiver

tcpclient: tcpclient.c
	$(CC) $(CFLAGS) tcpclient.c -o tcpclient

tcpserver: tcpserver.c
	$(CC) $(CFLAGS) tcpserver.c -o tcpserver

udpsender: udpsender.c
	$(CC) $(CFLAGS) udpsender.c -o udpsender

udpreceiver: udpreceiver.c
	$(CC) $(CFLAGS) udpreceiver.c -o udpreceiver

clean:
	rm -f tcpclient tcpserver udpsender udpreceiver
	rm -f *~
