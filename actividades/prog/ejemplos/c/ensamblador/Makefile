#Fichero: Makefile
#Compilar con:
# $make

CC = gcc

FUENTE = principal.c
EXE = main

main:
	$(CC) $(FUENTE) -o $(EXE)
	
fase1:
	$(CC) $(FUENTE) -o $(EXE).i -E

fase2: fase1
	$(CC) $(EXE).i -o $(EXE).s -S
	
