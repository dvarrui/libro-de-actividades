/* This file is released into the public domain
   with no warranty whatsoever. Enjoy. */

#ifndef FBMODEDB_H
#define FBMODEDB_H


/* Structure for /etc/fb.modes modelines. The parser returns
   a linked list of these structures. */
typedef struct FbModeline {
	char *name;
	int xres, yres, vxres, vyres;
	int depth;
	int dotclock, left, right, lower, upper;
	int hslen, vslen;
	int hsync, vsync, csync, extsync, doublescan, laced;
	struct FbModeline *next;
} FbModeline;


FbModeline *FB_ParseModeDB(char *filename);
/* Reads the given file (usually /etc/fb.modes) and returns a
   linked list of FbModeline structures. Returns NULL on failure.
   Minor syntax errors are not considered failure; the parser
   tries to deal with corrupt mode definitions in a sane way. */

void FB_FreeModeDB(FbModeline *modelist);
/* Frees a linked list of FbModeline structures. */

void FB_PrintModeDB(FbModeline *modelist);
/* Prints a linked list of FbModeline structures to stdout.
   Intended primarily for debugging. */

#endif
