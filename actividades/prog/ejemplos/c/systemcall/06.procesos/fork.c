#include <sys/types.h>

main() {
	int i=0;
	
	switch(fork()) {
	case -1:
		perror("Error al crear procesos");
		exit (-1);
		break;
	case 0:
		while(i<10) {
			sleep(3);
			printf("\t\tsoy el proceso hijo. %d\n",i++);
		}
		break;
	default:
		while(i<10) {
			printf("Soy el proceso padre: %d\n",i++);
			sleep(2);
		}
	};
	exit(0);
}
