/**
 *
 **/

#include <stdio.h>
#include <fcntl.h>

char buffer [BUFSIZ];

int main (int argc, char *argv [])
{
	int fd_origen, fd_destino;
	int nbytes;

	if (argc!=3) {
		fprintf(stderr,"Forma de uso: %s origen destino\n",argv[0]);
		return -1;
	}
	if ((fd_origen=open(argv[1],O_RDONLY))==-1) {
		perror(argv[1]);
		return -1;
	}

	if ((fd_destino=open(argv[2],O_WRONLY|O_TRUNC|O_CREAT,0666))==-1) {
		perror(argv[1]);
		return -1;
	}

	while((nbytes=read(fd_origen, buffer, sizeof buffer))>0)
		write (fd_destino, buffer, nbytes);
	close(fd_origen);
	close(fd_destino);
	return 0;
}
