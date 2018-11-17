#ifndef NETWORK_H
#define NETWORK_H

#include <SDL/SDL.h>   /* for datatypes */
#include "gamedefs.h"

/* Data structures for network update packets.
   All values are in NETWORK byte order. */

/* Since computer architectures may differ in floating point
   representation, we never send floats or doubles over the wire.
   Instead, we use the following macros to convert these values
   to a 32-bit integer representation (this is known as "fixed point").
   These macros also handle the byte order conversion. */

#include <netinet/in.h>  /* htonl and ntohl macros */

#define DOUBLE_TO_NET(dbl) (htonl((Sint32)((double)dbl*65536.0)))
#define NET_TO_DOUBLE(net) ((double)ntohl(net)/65536.0)

typedef struct net_pkt_s {
    Sint32 my_x, my_y;   /* Current position of the player that's
			    sending this packet. */
    Sint32 my_angle;     /* Angle. */
    Sint32 my_velocity;  /* Velocity. */
    Uint8 fire;          /* Player sending this packet is firing. */ 
    Uint8 respawn;       /* Player sending this packet has respawned. */
    Uint8 hit;           /* You (recipient) have been hit. */
    Uint8 dead;          /* You (recipient) should explode and respawn. */
} net_pkt_t, *net_pkt_p;

/* Structure for representing network connections. */
typedef struct net_link_s {
    int sock;                /* Connected socket. */
    char dotted_ip[15];      /* Printable form of the peer's address. */
    struct sockaddr_in addr; /* Peer's address structure. */
} net_link_t, *net_link_p;

/* Utility functions for working with network packets. */

void CreateNetPacket(net_pkt_p pkt, player_p player, int hit, int dead, int respawn);
/* Fills in a network game packet based on a player's current information.
   hit indicates that the remote player has been hit and should deduct the
     standard hit value (defined elsewhere) from its shields.
   dead indicates that the remote player has been destroyed and should
     respawn.
   respawn indicates that the local player has respawned. */

void InterpretNetPacket(net_pkt_p pkt,
			double *remote_x, double *remote_y,
			double *remote_angle, double *remote_velocity,
			int *firing, int *hit, int *dead, int *respawn);
/* Extracts the encoded (big endian swapped) values from a network game
   packet. */


int ConnectToNetgame(char *hostname, int port, net_link_p link);
/* Connects to a Penguin Warrior server at the given hostname and port.
   Fills in the given link structure with connection information.
   Returns 0 on success, -1 on failure. */

int WaitNetgameConnection(int port, net_link_p link);
/* Waits for a Penguin Warrior client to connect on the given port.
   Fills in the given link structure with connection information.
   Returns 0 on success, -1 on failure. */

int ReadNetgamePacket(net_link_p link, net_pkt_p pkt);
/* Reads a net packet from the remote player. If none has arrived yet,
   waits. This is by definition a blocking function, so don't call it
   from the main loop unless you want the game's framerate to depend
   on the network speed (bad idea).
   Returns 0 on success, -1 on failure. Failure means that the
   connection has been lost. */

int WriteNetgamePacket(net_link_p link, net_pkt_p pkt);
/* Sends a net packet to the remote player. This is a blocking function,
   so it would be wise to call it from a separate thread.
   Returns 0 on success, -1 on failure. Failure means that the
   connection has been lost. */

void CloseNetgameLink(net_link_p link);
/* Closes a network game connection. */

#endif
