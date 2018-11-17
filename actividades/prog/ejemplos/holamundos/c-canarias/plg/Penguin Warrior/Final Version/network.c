#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <string.h>
#include <errno.h>
#include <netdb.h>
#include "network.h"

void CreateNetPacket(net_pkt_p pkt, player_p player, int hit, int dead, 
		     int respawn)
{
    /* Fill in all of the relevant values, calling
       our conversion macro to preclude endianness
       problems. */
    pkt->my_x = DOUBLE_TO_NET(player->world_x);
    pkt->my_y = DOUBLE_TO_NET(player->world_y);
    pkt->my_angle = DOUBLE_TO_NET(player->angle);
    pkt->my_velocity = DOUBLE_TO_NET(player->velocity);
    pkt->fire = (Uint8)player->firing;
    pkt->hit = (Uint8)hit;
    pkt->dead = (Uint8)dead;
    pkt->respawn = (Uint8)respawn;
}

void InterpretNetPacket(net_pkt_p pkt,
			double *remote_x, double *remote_y,
			double *remote_angle, double *remote_velocity,
			int *firing, int *hit, int *dead, int *respawn)
{
    /* Decode the values in the packet and store
       them in the appropriate places. */
    *remote_x = NET_TO_DOUBLE(pkt->my_x);
    *remote_y = NET_TO_DOUBLE(pkt->my_y);
    *remote_angle = NET_TO_DOUBLE(pkt->my_angle);
    *remote_velocity = NET_TO_DOUBLE(pkt->my_velocity);
    *firing = (int)pkt->fire;
    *hit = (int)pkt->hit;
    *dead = (int)pkt->dead;
    *respawn = (int)pkt->respawn;
}

int ConnectToNetgame(char *hostname, int port, net_link_p link)
{
    int sock;
    struct sockaddr_in addr;
    struct hostent *hostlist;

    /* Resolve the host's address with DNS. */
    hostlist = gethostbyname(hostname);
    if (hostlist == NULL || hostlist->h_addrtype != AF_INET) {
	fprintf(stderr, "Unable to resolve %s: %s\n",
		hostname,
		strerror(errno));
	return -1;
    }
	
    /* Save the dotted IP address in the link structure. */
    inet_ntop(AF_INET, hostlist->h_addr_list[0], link->dotted_ip, 15);

    /* Load the address structure with the server's info. */
    memset(&addr, 0, sizeof (struct sockaddr_in));
    addr.sin_family = AF_INET;
    memcpy(&addr.sin_addr, hostlist->h_addr_list[0], hostlist->h_length);
    addr.sin_port = htons(port);

    /* Create a TCP stream socket. */
    sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (sock < 0) {
	fprintf(stderr, "Unable to create socket: %s\n",
		strerror(errno));
	return -1;
    }
	
    printf("Attempting to connect to %s:%i...\n",
	   link->dotted_ip, port);

    /* Ready to go! Connect to the remote machine. */
    if (connect(sock, (struct sockaddr *)&addr, sizeof (addr)) < 0) {
	fprintf(stderr, "Unable to connect: %s\n",
		strerror(errno));
	close(sock);
	return -1;
    }

    /* Copy the socket and the address into the link structure. */
    link->sock = sock;
    link->addr = addr;

    printf("Connected!\n");

    return 0;
}

int WaitNetgameConnection(int port, net_link_p link)
{
    int listener, sock;
    struct sockaddr_in addr;
    socklen_t addr_len;

    /* Create a listening socket. */
    listener = socket(PF_INET, SOCK_STREAM, IPPROTO_IP);
    if (listener < 0) {
	fprintf(stderr, "Unable to create socket: %s\n",
		strerror(errno));
	return -1;
    }

    /* Set up the address structure for the listener. */
    addr_len = sizeof (addr);
    memset(&addr, 0, addr_len);
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);

    /* Bind the listener to a local port. */
    if (bind(listener, (struct sockaddr *)&addr, addr_len) < 0) {
	fprintf(stderr, "Unable to bind to port %i: %s\n",
		port, strerror(errno));
	close(listener);
	return -1;
    }

    /* Make this a listening socket. */
    if (listen(listener, 1) < 0) {
	fprintf(stderr, "Unable to listen: %s\n",
		strerror(errno));
	close(listener);
	return -1;
    }

    printf("Waiting for connection on port %i.\n", port);

    /* Accept a connection. */
    if ((sock = accept(listener, (struct sockaddr *)&addr, &addr_len)) < 0) {
	fprintf(stderr, "Unable to accept connection: %s\n",
		strerror(errno));
	close(listener);
	return -1;
    }

    /* Ready to go! Save this info in the link structure. */
    link->sock = sock;
    link->addr = addr;
    inet_ntop(AF_INET, &addr.sin_addr, link->dotted_ip, 15);	

    printf("Connected!\n");

    return 0;
}

int ReadNetgamePacket(net_link_p link, net_pkt_p pkt)
{
    int remaining, count;

    remaining = sizeof (struct net_pkt_s);
    count = 0;

    /* Loop until a complete packet arrives.
       This could block indefinitely, but it
       typically won't, and it's of less importance
       since the networking code runs in a separate
       thread. */
    while (remaining > 0) {
	int amt;

	/* Read as much as possible. */
	amt = read(link->sock, ((char *)pkt)+count, remaining);

	/* If read returns a positive value, or zero
	   with errno == EINTR, there is no error. */
	if (amt <= 0 && errno != EINTR) {
	    fprintf(stderr, "ReadNetgamePacket: read failed: %s\n",
		    strerror(errno));
	    return -1;
	}
		
	/* Increment the counters by the amount read. */
	remaining -= amt;
	count += amt;
    }
      
    return 0;
}

int WriteNetgamePacket(net_link_p link, net_pkt_p pkt)
{
    int remaining, count;

    remaining = sizeof (struct net_pkt_s);
    count = 0;

    /* Loop until we've written the entire packet. */
    while (remaining > 0) {
	int amt;

	/* Try to write the rest of the packet.
	   Note the amount that was actually written. */
	amt = write(link->sock, ((char *)pkt)+count, remaining);

	/* Same error semantics as ReadNetgamePacket. */
	if (amt <= 0 && errno != EINTR) {
	    fprintf(stderr, "WriteNetgamePacket: read failed: %s\n",
		    strerror(errno));
	    return -1;
	}
		
	/* Increments the counters by the number of
	   bytes written. */
	remaining -= amt;
	count += amt;
    }
      
    return 0;
}

void CloseNetgameLink(net_link_p link)
{
    /* Close the socket connection. */
    close(link->sock);
    link->sock = -1;
}
