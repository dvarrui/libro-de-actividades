LIBDIR = -L/usr/X11R6/lib
CC = gcc
CFLAGS = $(COMPILERFLAGS)
#LIBRARIES = -lX11 -lXi -lXmu -lglut -lGL -lGLU -lm
LIBRARIES = -lX11 -lglut -lGL -lGLU -lm

transform : transform.o matrixmath.o vectormath.o
	$(CC) $(CFLAGS) -o $@ $(LIBDIR) $? $(LIBRARIES)

matrixmath.o : ../../common/matrixmath.c
	$(CC) $(CFLAGS) -c $<

vectormath.o : ../../common/vectormath.c
	$(CC) $(CFLAGS) -c $<

clean:
	rm -f *.o
