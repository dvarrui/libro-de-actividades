LIBDIR = -L/usr/X11R6/lib
CC = gcc
CFLAGS = $(COMPILERFLAGS)
#LIBRARIES = -lX11 -lXi -lXmu -lglut -lGL -lGLU -lm
LIBRARIES = -lX11 -lglut -lGL -lGLU -lm

sphereworld : sphereworld.o framemath.o vectormath.o torus.o matrixmath.o
	$(CC) $(CFLAGS) -o $@ $(LIBDIR) $? $(LIBRARIES)

framemath.o : ../../common/framemath.c matrixmath.o
	$(CC) $(CFLAGS) -c $<

vectormath.o : ../../common/vectormath.c
	$(CC) $(CFLAGS) -c $<

matrixmath.o : ../../common/matrixmath.c
	$(CC) $(CFLAGS) -c $<

torus.o : ../../common/torus.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f *.o
