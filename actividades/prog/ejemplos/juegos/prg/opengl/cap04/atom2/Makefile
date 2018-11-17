LIBDIR = -L/usr/X11R6/lib
CC = gcc
CFLAGS = $(COMPILERFLAGS)
#LIBRARIES = -lX11 -lXi -lXmu -lglut -lGL -lGLU -lm
LIBRARIES = -lX11 -lglut -lGL -lGLU -lm

atom2 : atom2.o
	$(CC) $(CFLAGS) -o $@ $(LIBDIR) $? $(LIBRARIES)

clean:
	rm -f *.o
