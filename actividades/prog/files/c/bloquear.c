/***
 *
 *
***/

#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define EQ(str1,str2) (strcmp((str1),(str2))==0)

main(int argc, char *argv[])
{
	int fd;

	if (argc!=3) {
		fprintf(stderr,"Forma de uso: %s modo fichero\n",argv[0]);
		fprintf(stderr," modo: -a [consultivo]\n");
		fprintf(stderr,"       -m [obligatorio]\n");
		exit(-1);
	}

	if (EQ (argv[1],"-a")) {
		if (lockf(fd,F_TLOCK,OL)==-1) {
			fprintf(stderr,"El fichero [%s] está bloqueado por otro proceso.\n",argv[2]);
			exit(-1);
		}
	} else if (EQ (argv[1],"-m"))
		if (lockf (fd,F_LOCK,OL)==-1) {
			perror(argv[2]);
			exit(-1);
		}

	printf("PID: %d. El fichero [%s] estará bloqueado\ndurante 30 segundos.\n", getpid(), argv[2]);
	sleep(30);

	if (lockf(fd,F_ULOCK,OL)==--1) {
		perror("lockf");
		exit(-1);
	} else
		printf ("\007PID: %d. El fichero [%s] ha sido desbloqueado.\n", getpid(), argv[2]);
	close(fd);
}

}
