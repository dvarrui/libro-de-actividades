#Fichero: Makefile
#Compilar con:
#	$make

LIB = -lSDL
CC = g++
SRC = src/principal.cpp src/juego.cpp src/pantalla.cpp src/frames.cpp src/entrada.cpp src/sprite.cpp
EJECUTABLE = juego

main: 
	$(CC) $(LIB) $(FLAGS) $(SRC) -o $(EJECUTABLE)

debug: 
	$(CC) $(LIB) -g $(SRC) -o $(EJECUTABLE)

all: main
	./juego

#CFLAGS = -lSDL -lSDL_ttf -lSDL_image 
#FLAGS = -D_REENTRANT
#LIB = -L/usr/lib -lSDL -lpthread -lm -L/usr/X11R6/lib -lX11 -lXext -ldl -I/usr/include/SDL
#LIB = -lSDL -lSDL_mixer
#OBJ = csprite.o
#SRC= src/*.cpp

#mapa: 
#	$(CC) src/crearmapa.cpp -o crearmapa
