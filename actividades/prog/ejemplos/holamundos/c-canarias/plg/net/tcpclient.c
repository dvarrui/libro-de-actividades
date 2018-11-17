/* A simple TCP/IP client program that uses sockets. */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>     /* Nice to have around for various UNIX goodies. */
#include <netinet/in.h> /* Internet structures and functions. */
#include <netdb.h>      /* Access to DNS lookups. */
#include <arpa/inet.h>  /* inet_ntop function. */
#include <sys/socket.h> /* Socket functions. */

struct hostent *hostlist;   /* List of hosts returned
			       by gethostbyname. */
char dotted_ip[15];         /* Buffer for converting
			       the resolved address to
			       a readable format. */
int port;                   /* Port number. */
int sock;                   /* Our connection socket. */
struct sockaddr_in sa;      /* Connection address. */


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

    /* Make sure we received two arguments,
       a hostname and a port number. */
    if (argc < 3) {
	printf("Simple TCP/IP client.\n");
	printf("Usage: %s <hostname or IP> <port>\n", argv[0]);
	return 1;
    }

    /* Look up the hostname with DNS. gethostbyname
       (at least most UNIX versions of it) properly
       handles dotted IP addresses as well as hostnames. */
    printf("Looking up %s...\n", argv[1]);
    hostlist = gethostbyname(argv[1]);
    if (hostlist == NULL) {
	printf("Unable to resolve %s.\n", argv[1]);
	return 1;
    }
	
    /* Good, we have an address. However, some sites
       are moving over to IPv6 (the newer version of
       IP), and we're not ready for it (since it uses
       a new address format). It's a good idea to check
       for this. */
    if (hostlist->h_addrtype != AF_INET) {
	printf("%s doesn't seem to be an IPv4 address.\n",
	       argv[1]);
	return 1;
    }

    /* inet_ntop converts a 32-bit IP address to
       the dotted string notation (suitable for printing).
       hostlist->h_addr_list is an array of possible addresses
       (in case a name resolves to more than on IP). In most
       cases we just want the first. */
    inet_ntop(AF_INET, hostlist->h_addr_list[0], dotted_ip, 15);
    printf("Resolved %s to %s.\n", argv[1], dotted_ip);

    /* Create a socket for the connection. */
    sock = socket(PF_INET, SOCK_STREAM, IPPROTO_IP);
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

    port = atoi(argv[2]);
    sa.sin_port = htons(port);
	
    /* The IP address was returned as a char * for various reasons.
       Just memcpy it into the sockaddr_in structure. */
    memcpy(&sa.sin_addr, hostlist->h_addr_list[0], hostlist->h_length);
	
    /* This is an Internet socket. */
    sa.sin_family = AF_INET;

    /* Connect! */
    printf("Trying %s on port %i...\n", dotted_ip, port);
    if (connect(sock, (struct sockaddr *)&sa, sizeof(sa)) < 0) {
	printf("Unable to connect: %s\n",
	       strerror(errno));
	return 1;
    }

    printf("Connected! Reading data. Press Control-C to quit.\n");
	
    /* Install a signal handler for Control-C (SIGINT).
       See the signal(2) manpage for more information. */
    signal(SIGINT, signal_handler);

    /* Read data until we encounter an error. */
    for (;;) {
	char ch;
	int amt;

	/* Read one byte at a time.
	   This is quite inefficient. */
	amt = read(sock, &ch, 1);

	/* ALWAYS check for errors on network reads.
	   They are MUCH less reliable than local
	   file accesses. */
	if (amt < 0) {
	    printf("\nRead error: %s\n",
		   strerror(errno));
	    break;
	} else if (amt == 0) {
	    /* A zero-byte read means the connection
	       has been closed. read waits until it
	       can return at least one byte. */
	    printf("\nConnection closed by remote system.\n");
	    break;
	}

	/* Write the character to stdout. */
	putchar(ch);
	fflush(stdout);
    }

    /* Close the connection. */
    printf("Closing socket.\n");
    close(sock);

    return 0;
}
