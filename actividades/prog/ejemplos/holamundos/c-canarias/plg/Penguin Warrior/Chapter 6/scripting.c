#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <tcl.h>
#include "scripting.h"

/* Our interpreter. This will be initialized by InitScripting. */
static Tcl_Interp *interp = NULL;

/* Prototype for the "fireWeapon" command handler. */
static int HandleFireWeaponCmd(ClientData client_data, Tcl_Interp * interp,
			       int objc, Tcl_Obj * CONST objv[]);

/* Ship data structures (from main.c). */
extern player_t player, opponent;

/* Sets up a Tcl interpreter for the game. Adds commands to implement our
   scripting interface. */
void InitScripting(void)
{

    /* First, create an interpreter and make sure it's valid. */
    interp = Tcl_CreateInterp();
    if (interp == NULL) {
	fprintf(stderr, "Unable to initialize Tcl.\n");
	exit(EXIT_FAILURE);
    }

    /* Add the "fireWeapon" command. */
    if (Tcl_CreateObjCommand(interp, "fireWeapon",
			     HandleFireWeaponCmd, (ClientData) 0,
			     NULL) == NULL) {
	fprintf(stderr, "Error creating Tcl command.\n");
	exit(EXIT_FAILURE);
    }

    /* Link the important parts of our player data structures to global
       variables in Tcl. (Ignore the char * typecast; Tcl will treat the data
       as the requested type, in this case double.) */
    Tcl_LinkVar(interp, "player_x", (char *) &player.world_x,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "player_y", (char *) &player.world_y,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "player_angle", (char *) &player.angle,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "player_accel", (char *) &player.accel,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "computer_x", (char *) &opponent.world_x,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "computer_y", (char *) &opponent.world_y,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "computer_angle", (char *) &opponent.angle,
		TCL_LINK_DOUBLE);
    Tcl_LinkVar(interp, "computer_accel", (char *) &opponent.accel,
		TCL_LINK_DOUBLE);

    /* Make the constants in gamedefs.h available to the script. The script
       should play by the game's rules, just like the human player.
       Tcl_SetVar2Ex is part of the Tcl_SetVar family of functions, which
       you can read about in the manpage. It simply sets a variable to a new
       value given by a Tcl_Obj structure. */
    Tcl_SetVar2Ex(interp, "world_width", NULL, Tcl_NewIntObj(WORLD_WIDTH),
		  0);
    Tcl_SetVar2Ex(interp, "world_height", NULL,
		  Tcl_NewIntObj(WORLD_HEIGHT), 0);
    Tcl_SetVar2Ex(interp, "player_forward_thrust", NULL,
		  Tcl_NewIntObj(PLAYER_FORWARD_THRUST), 0);
    Tcl_SetVar2Ex(interp, "player_reverse_thrust", NULL,
		  Tcl_NewIntObj(PLAYER_REVERSE_THRUST), 0);
}

/* Cleans up after our scripting system. */
void CleanupScripting(void)
{
    if (interp != NULL) {
	Tcl_DeleteInterp(interp);
    }
}

/* Executes a script in our customized interpreter. Returns 0 on success.
   Returns -1 and prints a message on standard error on failure.

   We'll use this to preload the procedures in the script. The interpreter's
   state is maintained after Tcl_EvalFile. We will NOT call Tcl_EvalFile after
   each frame - that would be hideously slow. */
int LoadGameScript(char *filename)
{
    int status;

    status = Tcl_EvalFile(interp, filename);
    if (status != TCL_OK) {
	fprintf(stderr, "Error executing %s: %s\n", filename,
		Tcl_GetStringResult(interp));
	return -1;
    }

    return 0;
}

/* Handles "fireWeapon" commands from the Tcl script. */
static int HandleFireWeaponCmd(ClientData client_data, Tcl_Interp *interp,
			       int objc, Tcl_Obj * CONST objv[])
{
    /* Avoid compiler warnings. */
    objc += 0;
    objv += 0;
    client_data += 0;

    /* Do nothing for now. We'll add weapons to the game later on. */
    fprintf(stderr, "Computer is firing weapon. This doesn't work yet.\n");

    /* Return nothing (but make sure it's a valid nothing). */
    Tcl_ResetResult(interp);

    /* Succeed. On failure we would set a result with Tcl_SetResult and
       return TCL_ERROR. */
    return TCL_OK;
}

/* Runs the game script's update function (named "playComputer").
   Returns 0 on success, -1 on failure. */
int RunGameScript()
{
    int status;

    /* Call the script's update procedure. */
    status = Tcl_Eval(interp, "playComputer");
    if (status != TCL_OK) {
	fprintf(stderr, "Error in script: %s\n",
		Tcl_GetStringResult(interp));
	return -1;
    }

    /* Enforce limits on the script. It can still "cheat" by turning its ship
       more quickly than the player or by directly modifying its position
       variables, but that's not too much of a problem. We can more or less
       trust the script (it's part of the game). */
    if (opponent.accel > PLAYER_FORWARD_THRUST)
	opponent.accel = PLAYER_FORWARD_THRUST;
    if (opponent.accel < PLAYER_REVERSE_THRUST)
	opponent.accel = PLAYER_REVERSE_THRUST;
    while (opponent.angle >= 360)
	opponent.angle -= 360;
    while (opponent.angle < 0)
	opponent.angle += 360;

    return 0;
}
