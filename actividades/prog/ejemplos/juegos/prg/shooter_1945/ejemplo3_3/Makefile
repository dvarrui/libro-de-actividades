#Fichero: Makefile
#Compilar con:
# $make

#INCLUDEDIR = /usr/include/SDL
#LIBDIR = /usr/lib
#BINDIR = .
#CFLAGS = -ISDL -lpthread
#CFLAGS = `sdl-config --cflags`

CFLAGS = -lSDL
CC = gcc

FUENTE = principal.c
EJECUTABLE = juego

main:
	$(CC) $(FUENTE) $(CFLAGS) -o $(EJECUTABLE)
	
fase1:
	$(CC) $(FUENTE) $(CFLAGS) -o $(EJECUTABLE).i -E

fase2: fase1
	$(CC) $(PROGRAMA).i $(CFLAGS) -o $(EJECUTABLE).s -s
	
