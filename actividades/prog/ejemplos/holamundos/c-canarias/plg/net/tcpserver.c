/* Simple TCP/IP server program. */

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

char dotted_ip[15];     /* Buffer for converting
			   the resolved address to
			   a readable format. */
int listener;           /* Our listening socket. */
int client;             /* The current client's socket. */
int port;               /* Port we're accepting connections on. */
struct sockaddr_in sa;  /* Connection address. */
socklen_t sa_len;       /* Length of sa. This is a bit redundant
			   in simple cases, but sockets aren't just
			   for TCP/IP. */

/* This function gets called whenever the user presses Control-C.
   See the signal(2) manpage for more information. */
void signal_handler(int signum)
{
    switch (signum) {
    case SIGINT:
	printf("\nReceived interrupt signal. Exiting.\n");
	close(client);
	close(listener);
	exit(0);
			
    default:
	printf("\nUnknown signal received. Ignoring.\n");
    }
}


int main(int argc, char *argv[])
{

    /* Make sure we received two arguments,
       a hostname and a port number. */
    if (argc < 2) {
	printf("Simple TCP/IP uptime server.\n");
	printf("Usage: %s <port>\n", argv[0]);
	return 1;
    }

    port = atoi(argv[1]);

    /* Create the listener socket. This socket will queue
       incoming connections. */
    listener = socket(PF_INET, SOCK_STREAM, IPPROTO_IP);
    if (listener < 0) {
	printf("Unable to create a listener socket: %s\n",
	       strerror(errno));
	return 1;
    }

    /* Now bind the listener to a local address. This uses
       the same sockaddr_in structure as connect. */
    sa_len = sizeof(sa);
    memset(&sa, 0, sa_len);
    sa.sin_family = AF_INET;
    sa.sin_port = htons(port);
    sa.sin_addr.s_addr = htonl(INADDR_ANY); /* Listen on all interfaces. */

    if (bind(listener, (struct sockaddr *)&sa, sa_len) < 0) {
	printf("Unable to bind to port %i: %s\n",
	       port,
	       strerror(errno));
	return 1;
    }

    /* Let the networking system know we're accepting
       connections on this socket. Ask for a connection
       queue of five clients. (If more than five clients
       try to connect before we call accept, some will
       be denied.) */
    if (listen(listener, 5) < 0) {
	printf("Unable to listen: %s\n",
	       strerror(errno));
	return 1;
    }

    /* Ready! Now accept connections until the user presses
       Control-C. */
    signal(SIGINT, signal_handler);

    for (;;) {
	char sendbuf[1024];
	int sent, length;
	FILE *uptime;

	client = accept(listener, (struct sockaddr *)&sa, &sa_len);
	if (client < 0) {
	    printf("Unable to accept: %s\n",
		   strerror(errno));
	    close(listener);
	    return 1;
	}

	/* We now have a live client. Print information
	   about it and then send something over the wire. */
	inet_ntop(AF_INET, &sa.sin_addr, dotted_ip, 15);
	printf("Received connection from %s.\n", dotted_ip);

	/* Use popen to retrieve the output of the
	   uptime command. This is a bit of a hack, but
	   it's portable and it works fairly well.
	   popen opens a pipe to a program (that is, it
	   executes the program and redirects its I/O
	   to a file handle). */
	uptime = popen("/usr/bin/uptime", "r");
	if (uptime == NULL) {
	    strcpy(sendbuf, "Unable to read system's uptime.\n");
	} else {
	    sendbuf[0] = '\0';
	    fgets(sendbuf, 1023, uptime);
	    pclose(uptime);
	}
		
	/* Figure out how much data we need to send. */
	length = strlen(sendbuf);
	sent = 0;

	/* Repeatedly call write until the entire
	   buffer is sent. */
	while (sent < length) {
	    int amt;

	    amt = write(client, sendbuf+sent, length-sent);

	    if (amt <= 0) {
				/* Zero-byte writes are OK if they
				   are caused by signals (EINTR).
				   Otherwise they mean the socket
				   has been closed. */
		if (errno == EINTR)
		    continue;
		else {
		    printf("Send error: %s\n",
			   strerror(errno));
		    break;
		}
	    }

	    /* Update our position by the number of
	       bytes that were sent. */
	    sent += amt;
	}

	close(client);
    }

    return 0;
}
