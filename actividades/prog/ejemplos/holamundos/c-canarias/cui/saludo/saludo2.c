#include <stdio.h>

int main(int argc, char *argv[]) {
	if (argc>1) {
		printf("Hola %s!\n",argv[1]);
	} else {
		printf("No le conozco.\n");
	}
	return 0;
}

