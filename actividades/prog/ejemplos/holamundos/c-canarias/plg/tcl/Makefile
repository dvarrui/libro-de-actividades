CFLAGS=-W -Wall -ggdb -O2
CC=gcc

# NOTE: this is likely to be different on your system!
# Try 'locate libtcl' if you're not sure.
TCL_LIB=-ltcl


.PHONY: clean

default:
	@echo 'Please check the TCL_LIB variable in the Makefile, then run make all.'

clean:
	rm -f tclshell tclshell2
	rm -f *~

all: tclshell tclshell2

tclshell: tclshell.c
	$(CC) $(CFLAGS) tclshell.c -o tclshell $(TCL_LIB)

tclshell2: tclshell2.c
	$(CC) $(CFLAGS) tclshell2.c -o tclshell2 $(TCL_LIB)
