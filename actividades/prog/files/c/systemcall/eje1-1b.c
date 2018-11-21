#include <stdio.h>
#include <string.h>
#include <errno.h>

extern int errno;

main() {
	int i;
	char msg[255];
	

	for (i=0; i<135; i++) {
		errno=i;
		sprintf(msg,"Error[%d]",i);
		perror(msg);
	}
}

