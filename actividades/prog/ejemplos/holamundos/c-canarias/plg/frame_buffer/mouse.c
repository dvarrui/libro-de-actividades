/* Very basic example of reading mouse data from GPM. */

#include <sys/time.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <gpm.h>

/* Signal handler and a global variable for detecting Ctrl-C and
   shutting down. */
int quit = 0;

void sighandler(int sig)
{
    sig++; /* prevent compiler warning */
    quit = 1;
}

int main(void)
{
    int gpm;
    Gpm_Connect gpm_connect;
    Gpm_Event gpm_event;
    char spinner[] = "|/-\\";
    int spinner_pos = 0;
	
    /* Exit on Ctrl-C. */
    signal(SIGINT, sighandler);

    /* This specifies the events we're interested in. */
    gpm_connect.eventMask = GPM_MOVE | GPM_UP | GPM_DOWN;

    /* Don't give a "default" treatment to any events. */
    gpm_connect.defaultMask = 0;

    /* These are used for multiple clients on a single console. */
    gpm_connect.minMod = 0;
    gpm_connect.maxMod = 0xFFFF;

    /* Connect to the GPM server. */
    gpm = Gpm_Open(&gpm_connect, 0);
    if (gpm < 0) {
	printf("Unable to connect to GPM.\n");
	return 1;
    }

    /* Now read mouse events until the user presses Ctrl-C. */
    while (!quit) {
	int result;
	struct timeval tv;
	fd_set fds;

	FD_ZERO(&fds);
	FD_SET(gpm, &fds);
	tv.tv_sec = 0;
	tv.tv_usec = 10000;

	result = select(gpm+1, &fds, NULL, NULL, &tv);
		
	if (result < 0) {
	    if (errno == EINTR) continue;
	    printf("select failed: %s\n",
		   strerror(errno));
	    break;
	}

	if (result > 0) {
	    /* Get the next mouse event. */
	    if (Gpm_GetEvent(&gpm_event) != 1) {
		printf("Unable to read an event.\n");
		break;
	    }
			
	    /* Print out the event.
	       X and Y are the coordinates of the mouse pointer.
	       DX and DY are the relative motion of the mouse since
	       the last event. Games are probably more interested in
	       this than the actual position.
	       Buttons gives a bitmask of pressed mouse buttons. */
	    printf("X: %4i  Y: %4i  DX: %4i  DY: %4i  Buttons: %3i\n",
		   gpm_event.x, gpm_event.y,
		   gpm_event.dx, gpm_event.dy,
		   gpm_event.buttons);
	} else {
	    /* If no data is ready, burn time with a dumb little
	       spinning character. */
	    printf("%c\r", spinner[spinner_pos++]);
	    fflush(stdout);
	    if (spinner[spinner_pos] == '\0') spinner_pos = 0;
	}
    }

    /* Shut down the GPM connection. */
    Gpm_Close();

    return 0;
}
