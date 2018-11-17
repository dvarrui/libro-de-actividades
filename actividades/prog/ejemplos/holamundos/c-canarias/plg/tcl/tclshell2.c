#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tcl.h>

int FooHandler(ClientData clientData, Tcl_Interp * interp, int objc,
	       Tcl_Obj * CONST objv[])
{
    static char *buf = "This is a result.";
    static char *errmsg = "foo requires at least one argument.";
    int i;

    /* Avoid compiler warning. */
    clientData += 0;

    /* Make sure we received at least two arguments. The first argument
       is always the name of the command, so this is really just one
       additional argument. */
    if (objc < 2) {
	/* Set the result to an error message. */
	Tcl_SetObjResult(interp, Tcl_NewStringObj(errmsg, strlen(errmsg)));

	/* Return with a bad status. This will cause the interpreter
	   to abort whatever it was doing (unless this command was inside
	   a catch { } block). */
	return TCL_ERROR;
    }

    /* Display the number of arguments. */
    printf("foo was called with %i arguments.\n", objc);

    /* Display each argument as a string. */
    for (i = 0; i < objc; i++) {
	printf("  argument %i is '%s'\n", i, Tcl_GetString(objv[i]));
    }

    /* Set the result to a string (defined above). Note that the
       string is declared static (so its pointer will remain valid
       after this function exits). */
    Tcl_SetObjResult(interp, Tcl_NewStringObj(buf, strlen(buf)));

    /* Return successfully. */
    return TCL_OK;
}

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
    Tcl_CreateObjCommand(interp, "foo", FooHandler, (ClientData) 0, NULL);

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
