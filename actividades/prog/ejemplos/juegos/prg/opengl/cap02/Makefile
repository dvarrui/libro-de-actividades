
#LIBDIR = -L/usr/X11R6/lib
CC = gcc
#CFLAGS = $(COMPILERFLAGS)
#LIBRARIES = -lX11 -lXi -lXmu -lglut -lGL -lGLU -lm
LIBRARIES = -lglut -lGL

main:
	$(CC) 01-simple.c $(LIBRARIES) -o 01-simple.exe
	$(CC) 02-rect.c $(LIBRARIES) -o 02-rect.exe
	$(CC) 03-bounce.c $(LIBRARIES) -o 03-bounce.exe

clean:
	rm -f *.exe
