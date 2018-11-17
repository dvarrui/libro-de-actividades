/* A simple but functional Tcl shell. */

#include <stdio.h>
#include <stdlib.h>
#include <tcl.h>

int main()
{
    Tcl_Interp *interp;
    char input[16384];

    /* Create an interpreter structure. */
    interp = Tcl_CreateInterp();
    if (interp == NULL) {
	printf("Unable to create interpreter.\n");
	return 1;
    }

    /* Add custom commands here. */

    /* Loop indefinitely (we'll break on end-of-file). */
    for (;;) {
	int result;
	char *message;

	/* Print a prompt and make it appear immediately. */
	printf("> ");
	fflush(stdout);

	/* Read up to 16k of input. */
	if (fgets(input, 16383, stdin) == NULL) {
	    printf("End of input.\n");
	    break;
	}

	/* Evaluate the input as a script. */
	result = Tcl_Eval(interp, input);

	/* Print the return code and the result. */
	switch (result) {
	case TCL_OK:
	    message = " OK ";
	    break;
	case TCL_ERROR:
	    message = "ERR ";
	    break;
	case TCL_CONTINUE:
	    message = "CONT";
	    break;
	default:
	    message = " ?? ";
	    break;
	}

	printf("[%s] %s\n", message, Tcl_GetStringResult(interp));
    }

    /* Delete the interpreter. */
    Tcl_DeleteInterp(interp);

    return 0;
}
