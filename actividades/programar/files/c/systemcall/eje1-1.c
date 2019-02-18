#include <stdio.h>
#include <string.h>

main() {
	int i;

	for (i=0; i<135; i++)
		printf("%d: %s\n", i, strerror(i));
}

