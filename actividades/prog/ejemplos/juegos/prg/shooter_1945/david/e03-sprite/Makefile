#Fichero: Makefile
#Compilar con:
#	$make

LIB = -lSDL
CC = g++

SRC = src/principal.cpp src/juego.cpp src/pantalla.cpp src/frames.cpp src/entrada.cpp src/sprite.cpp
EJECUTABLE = juego

main: 
	$(CC) $(LIB) $(SRC) -o $(EJECUTABLE)

debug: 
	$(CC) $(LIB) -g $(SRC) -o $(EJECUTABLE)

all: main
	./juego
