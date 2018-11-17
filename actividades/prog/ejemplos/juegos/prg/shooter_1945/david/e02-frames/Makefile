#Fichero: Makefile
#Compilar con:
#	$make

LIB = -lSDL
CC = g++

SRC = src/principal.cpp src/pantalla.cpp src/entrada.cpp src/frames.cpp
EJECUTABLE = juego

main: 
	$(CC) $(LIB) $(SRC) -o $(EJECUTABLE)

debug: 
	$(CC) $(LIB) -g $(SRC) -o $(EJECUTABLE)
