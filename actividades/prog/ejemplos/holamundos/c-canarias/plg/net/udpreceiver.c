/* Simple UDP packet receiver. */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int port;                   /* Port number. */
int sock;                   /* Our connection socket. */
struct sockaddr_in sa;      /* Connection address. */
socklen_t sa_len;           /* Size of sa. */

/* This function gets called whenever the user presses Control-C.
   See the signal(2) manpage for more information. */
void signal_handler(int signum)
{
    switch (signum) {
    case SIGINT:
	printf("\nReceived interrupt signal. Exiting.\n");
	close(sock);
	exit(0);
			
    default:
	printf("\nUnknown signal received. Ignoring.\n");
    }
}

int main(int argc, char *argv[])
{

    /* Make sure we received one argument,
       the port number to listen on. */
    if (argc < 2) {
	printf("Simple UDP datagram receiver.\n");
	printf("Usage: %s <port>\n", argv[0]);
	return 1;
    }

    /* Create a SOCK_DGRAM socket. */
    sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_IP);
    if (sock < 0) {
	printf("Unable to create a socket: %s\n",
	       strerror(errno));
	return 1;
    }

    /* Fill in the sockaddr_in structure. The address is already
       in network byte order (from the gethostbyname call).
       We need to convert the port number with the htons macro.
       Before we do anything else, we'll zero out the entire
       structure. */
    memset(&sa, 0, sizeof(struct sockaddr_in));

    port = atoi(argv[1]);
    sa.sin_port = htons(port);
    sa.sin_addr.s_addr = htonl(INADDR_ANY);  /* listen on all interfaces */
	
    /* This is an Internet socket. */
    sa.sin_family = AF_INET;

    /* Bind to a port so the networking software will know
       which port we're interested in receiving packets from. */
    if (bind(sock, (struct sockaddr *)&sa, sizeof(sa)) < 0) {
	printf("Error binding to port %i: %s\n",
	       port,
	       strerror(errno));
	return 1;
    }

    printf("Listening for UDP packets. Press Ctrl-C to exit.\n");
	
    /* Install a signal handler for Control-C (SIGINT).
       See the signal(2) manpage for more information. */
    signal(SIGINT, signal_handler);

    /* Collect packets until the user pressed Ctrl-C. */
    for (;;) {

	char buf[255];

	/* Receive the next datagram. */
	if (recvfrom(sock,      /* UDP socket */
		     buf,       /* receive buffer */
		     255,       /* max bytes to receive */
		     0,         /* no special flags */
		     (struct sockaddr *)&sa,       /* sender's address */
		     &sa_len) < 0) {
	    printf("Error receiving packet: %s\n",
		   strerror(errno));
	    return 1;
	}

	/* Announce that we've received something. */
	printf("Got message: '%s'\n", buf);
    }

    /* This will never be reached. */
    return 0;
}
