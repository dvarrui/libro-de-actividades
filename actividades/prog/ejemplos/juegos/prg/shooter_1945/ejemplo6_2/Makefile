#Fichero: Makefile
#Compilar con:
# $make

#CFLAGS = -lSDL -lSDL_ttf -lSDL_image 
#FLAGS = -D_REENTRANT
#LIB = -L/usr/lib -lSDL -lpthread -lm -L/usr/X11R6/lib -lX11 -lXext -ldl -I/usr/include/SDL
LIB = -lSDL -lSDL_mixer
#OBJ = csprite.o
CC = g++

SRC = principal.cpp csprite.cpp
EJECUTABLE = juego

main: 
	$(CC) $(LIB) $(FLAGS) $(SRC) -o $(EJECUTABLE)

mapa: 
	$(CC) crearmapa.cpp -o crearmapa
