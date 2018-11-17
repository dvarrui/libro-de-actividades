/* This file is released into the public domain
   with no warranty whatsoever. Enjoy. */

/* Parser for the Linux fbdev /etc/fb.modes database. */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include "fbmodedb.h"

#define MAXTOKLEN 1024

/* Token types. */
typedef enum {
    TOK_NONE,
    TOK_EOF,
    TOK_ERROR,
    TOK_KEYWORD,
    TOK_STRING,
    TOK_INTEGER
} TokenType;


/* File-based stream of tokens. */
typedef struct TokenStream {
    char token[MAXTOKLEN];
    TokenType type;
    FILE *file;
} TokenStream;

/* Recursive-descent parser return codes. */
typedef enum {
    PARSE_OK,     /* success */
    PARSE_BAD,    /* failure, no tokens consumed  (try a different rule) */
    PARSE_SYNTAX, /* syntax error after eating tokens (eek!) */
    PARSE_FATAL   /* non-grammar error (memory allocation, etc) */
} ParseResult;

#define DIGITS "0123456789"
#define WHITESPACE "\n\r\t "

/* Opens a TokenStream from a file.
   Returns 0 on success, -1 on failure. */
static int OpenTokenStream(TokenStream *tokstream, char *filename)
{
    FILE *f;

    f = fopen(filename, "r");
    if (f == NULL) return -1;
    tokstream->file = f;
    tokstream->type = TOK_NONE;
    tokstream->token[0] = '\0';
	
    return 0;
}

/* Closes the file associated with a TokenStream. */
static void CloseTokenStream(TokenStream *tokstream)
{
    if (tokstream->file != NULL) {
	fclose(tokstream->file);
	tokstream->file = NULL;
    }
}

/* Consumes a token, allowing the next one to be read. */
static void EatToken(TokenStream *tokstream)
{
    tokstream->type = TOK_NONE;
}

/* Reads the next token from a TokenStream.
   Does nothing if the current token hasn't been consumed
   yet (with EatToken). Returns the type of token read,
   TOK_EOF if no more tokens are available, or TOK_ERROR
   if something bad happens. */
static TokenType ReadNextToken(TokenStream *tokstream)
{
    int pos = 0;
    int complete = 0;
    int gather = 0;
    int discard = 0;
    int putback = 0;
    enum { UNDECIDED = 0, COMMENT, QUOTED, INTEGER, SPACING,
	   KEYWORD, ACCEPT, ERROR } state = UNDECIDED;

    /* If we've hit EOF, keep returning that token. */
    if (feof(tokstream->file)) {
	tokstream->type = TOK_EOF;
	return TOK_EOF;
    }

    if (tokstream->type != TOK_NONE)
	return tokstream->type;

    /* I apologize for this spaghetti. It's an ordinary
       finite state machine lexer. */
    while ((state != ACCEPT) && (state != ERROR)) {
	int ch;
		
	gather = 0;
	putback = 0;

	ch = fgetc(tokstream->file);
	if (ch == EOF) {
	    if (complete){
		state = ACCEPT;
		continue;
	    } else {
		state = ERROR;
		break;
	    }
	}
		
	switch (state) {

	case UNDECIDED:
	    /* Decide on the token type. */
	    if (strchr(DIGITS, ch)) {
		tokstream->type = TOK_INTEGER;
		state = INTEGER;
		gather = 1;
		complete = 1;
	    } else if (ch == '"') {
		tokstream->type = TOK_STRING;
		state = QUOTED;
		complete = 0;
		gather = 0;
	    } else if (ch == '#') {
		state = COMMENT;
		complete = 1;
		gather = 0;
		discard = 1;
	    } else if (strchr(WHITESPACE, ch)) {
		state = SPACING;
		complete = 1;
		gather = 0;
		discard = 1;
	    } else {
		tokstream->type = TOK_KEYWORD;
		state = KEYWORD;
		complete = 1;
		gather = 1;
	    }
	    break;

	case COMMENT:
	    if (ch == '\n') state = ACCEPT;
	    gather = 0;
	    break;

	case SPACING:
	    if (!strchr(WHITESPACE, ch)) {
		state = ACCEPT;
		putback = 1;
		complete = 1;
		discard = 1;
		gather = 0;
	    }
	    break;

	case QUOTED:
		       
	    if (ch == '"') {
		state = ACCEPT;
		gather = 0;
		complete = 1;
	    } else {
		gather = 1;
	    }
	    break;

	case INTEGER:

	    if (strchr(DIGITS, ch)) {
		gather = 1;
	    } else if (strchr(WHITESPACE, ch)) {
		state = ACCEPT;
		gather = 0;
	    } else if (ch == '#') {
		state = ACCEPT;
		gather = 0;
		putback = 1;
	    } else {
		state = ERROR;
		gather = 0;
	    }
	    break;

	case KEYWORD:

	    if (strchr(WHITESPACE, ch)) {
		state = ACCEPT;
		gather = 0;
	    } else if (ch == '#') {
		state = ACCEPT;
		gather = 0;
		putback = 1;
	    } else {
		gather = 1;
	    }
	    break;

	default:
	    /* This should never happen. */
	    state = ERROR;
	}

	if (gather) {
	    if (pos >= MAXTOKLEN-3) {
		state = ERROR;
		break;
	    }
	    tokstream->token[pos] = ch;
	    tokstream->token[pos+1] = '\0';
	    pos++;
	}

	if (putback) {
	    ungetc(ch, tokstream->file);
	}
    }

    if (state == ERROR) {
	tokstream->type = TOK_ERROR;
	return TOK_ERROR;
    } else if (discard) {
	tokstream->type = TOK_NONE;
	return ReadNextToken(tokstream);
    }

    return tokstream->type;
}

/* Tries to match the current token in stream with the given keyword.
   If the match succeeds, eats the token and returns PARSE_OK.
   Otherwise returns PARSE_BAD and leaves the token in place. */
static ParseResult MatchKeyword(TokenStream *stream, char *keyword)
{
    ReadNextToken(stream);
    if (stream->type == TOK_KEYWORD && !strcasecmp(keyword, stream->token)) {
	EatToken(stream);
	return PARSE_OK;
    }
    return PARSE_BAD;
}

/* Verifies that the current token in stream is of the given type.
   Returns PARSE_OK if this is true, or PARSE_BAD if not. */
static ParseResult ExpectTokenType(TokenStream *stream, TokenType type)
{
    ReadNextToken(stream);
    if (stream->type == type)
	return PARSE_OK;
    return PARSE_BAD;
}

/* Allocates an FbModeline structure with the given name. */
static FbModeline *AllocModeline(char *name) {
    FbModeline *modeline;

    modeline = (FbModeline *)malloc(sizeof (FbModeline));
    if (modeline == NULL) return NULL;
    memset(modeline, 0, sizeof (FbModeline));
    modeline->name = strdup(name);
    if (modeline->name == NULL) {
	free(modeline);
	return NULL;
    }

    return modeline;
}

/* Frees a modeline structure. */
static void FreeModeline(FbModeline *modeline)
{
    free(modeline->name);
    free(modeline);
}

/* Parses a geometry line in a mode definition.
   Returns PARSE_OK and adjusts values in modeline on success.
   Returns PARSE_BAD if this isn't a geometry line.
   Returns PARSE_SYNTAX if this starts out like a geometry
   line but later proves to be syntactically incorrect. */
static ParseResult ParseModeGeometry(TokenStream *tokstream, FbModeline *modeline)
{
    if (MatchKeyword(tokstream, "geometry") != PARSE_OK) return PARSE_BAD;
	
    if (ExpectTokenType(tokstream, TOK_INTEGER)) return PARSE_SYNTAX;
    modeline->xres = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER)) return PARSE_SYNTAX;
    modeline->yres = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER)) return PARSE_SYNTAX;
    modeline->vxres = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER)) return PARSE_SYNTAX;
    modeline->vyres = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER)) return PARSE_SYNTAX;
    modeline->depth = atoi(tokstream->token);
    EatToken(tokstream);

    return PARSE_OK;
}


/* Parses a timings line in a mode definition. Same semantics
   as ParseModeGeometry. */
static ParseResult ParseModeTimings(TokenStream *tokstream, FbModeline *modeline)
{
    if (MatchKeyword(tokstream, "timings") != PARSE_OK) return PARSE_BAD;
	
    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->dotclock = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->left = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->right = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->lower = atoi(tokstream->token);
    EatToken(tokstream);
	
    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->upper = atoi(tokstream->token);
    EatToken(tokstream);

    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->hslen = atoi(tokstream->token);
    EatToken(tokstream);

    if (ExpectTokenType(tokstream, TOK_INTEGER) != PARSE_OK) return PARSE_SYNTAX;
    modeline->vslen = atoi(tokstream->token);
    EatToken(tokstream);

    return PARSE_OK;
}

/* Handles hsync, vsync, and laced specifications in mode definitions. */
static ParseResult ParseModeMisc(TokenStream *tokstream, FbModeline *modeline)
{
    if (MatchKeyword(tokstream, "hsync") == PARSE_OK) {
	if (MatchKeyword(tokstream, "high") == PARSE_OK) {
	    modeline->hsync = 1;
	} else if (MatchKeyword(tokstream, "low") == PARSE_OK) {
	    modeline->hsync = 0;
	} else return PARSE_SYNTAX;
    } else if (MatchKeyword(tokstream, "vsync") == PARSE_OK) {
	if (MatchKeyword(tokstream, "high") == PARSE_OK) {
	    modeline->vsync = 1;
	} else if (MatchKeyword(tokstream, "low") == PARSE_OK) {
	    modeline->vsync = 0;
	} else return PARSE_SYNTAX;
    } else if (MatchKeyword(tokstream, "csync") == PARSE_OK) {
	if (MatchKeyword(tokstream, "true") == PARSE_OK) {
	    modeline->csync = 1;
	} else if (MatchKeyword(tokstream, "false") == PARSE_OK) {
	    modeline->csync = 0;
	} else return PARSE_SYNTAX;
    } else if (MatchKeyword(tokstream, "extsync") == PARSE_OK) {
	if (MatchKeyword(tokstream, "true") == PARSE_OK) {
	    modeline->extsync = 1;
	} else if (MatchKeyword(tokstream, "false") == PARSE_OK) {
	    modeline->extsync = 0;
	} else return PARSE_SYNTAX;
    } else if (MatchKeyword(tokstream, "double") == PARSE_OK) {
	if (MatchKeyword(tokstream, "true") == PARSE_OK) {
	    modeline->doublescan = 1;
	} else if (MatchKeyword(tokstream, "false") == PARSE_OK) {
	    modeline->doublescan = 0;
	} else return PARSE_SYNTAX;
    } else if (MatchKeyword(tokstream, "laced") == PARSE_OK) {
	if (MatchKeyword(tokstream, "true") == PARSE_OK) {
	    modeline->laced = 1;
	} else if (MatchKeyword(tokstream, "false") == PARSE_OK) {
	    modeline->laced = 0;
	} else return PARSE_SYNTAX;
    } else return PARSE_BAD;
	
    return PARSE_OK;
}


/* Parses a framebuffer mode definition by recursive descent.
   Stores a pointer to the newly-allocated FbModeline structure
   in modeline. Returns the standard PARSE_foo result codes. */
static ParseResult ParseModeline(TokenStream *tokstream, FbModeline **modeline)
{
    FbModeline *tmp;

    if (MatchKeyword(tokstream, "mode") != PARSE_OK)
	return PARSE_BAD;

    if (ExpectTokenType(tokstream, TOK_STRING) != PARSE_OK)
	return PARSE_SYNTAX;

    tmp = AllocModeline(tokstream->token);
    if (tmp == NULL)
	return PARSE_FATAL;

    EatToken(tokstream);

    for (;;) {
	ParseResult result;

	result = ParseModeGeometry(tokstream, tmp);
	if (result == PARSE_OK) continue;
	if (result == PARSE_SYNTAX) goto recover;
	if (result != PARSE_BAD) return result;

	result = ParseModeTimings(tokstream, tmp);
	if (result == PARSE_OK) continue;
	if (result == PARSE_SYNTAX) goto recover;
	if (result != PARSE_BAD) return result;

	result = ParseModeMisc(tokstream, tmp);
	if (result == PARSE_OK) continue;
	if (result == PARSE_SYNTAX) goto recover;
	if (result != PARSE_BAD) return result;

	if (MatchKeyword(tokstream, "endmode") == PARSE_OK) break;

	if (tokstream->type == TOK_EOF) return PARSE_SYNTAX;

	/* Unrecognized token, so eat chars to EOL */
    recover:
	printf("Unrecognized token: %s\n", tokstream->token);
	while (!feof(tokstream->file) && (fgetc(tokstream->file) != '\n'));
	tokstream->type = TOK_NONE;
    }

    *modeline = tmp;

    return PARSE_OK;
}


/* Reads the given file (usually /etc/fb.modes) and returns a
   linked list of FbModeline structures. Returns NULL on failure.
   Minor syntax errors are not considered failure; the parser
   tries to deal with corrupt mode definitions in a sane way. */
FbModeline *FB_ParseModeDB(char *filename)
{
    TokenStream tokstream;
    FbModeline *modelist, *newmode;
    ParseResult result;

    if (OpenTokenStream(&tokstream, filename) != 0) return NULL;

    modelist = NULL;

    for (;;) {
	if (ReadNextToken(&tokstream) == TOK_EOF) break;
		
	result = ParseModeline(&tokstream, &newmode);
	if (result != PARSE_OK) {
	    printf("Parse error.\n");

	    /* Try to resync */
	    EatToken(&tokstream);
	    continue;
	}
		
	newmode->next = modelist;
	modelist = newmode;
    }
	
    CloseTokenStream(&tokstream);

    return modelist;
}


/* Frees a linked list of FbModeline structures. */
void FB_FreeModeDB(FbModeline *modelist)
{
    FbModeline *next;

    next = modelist;

    while (next != NULL) {
	next = modelist->next;
	FreeModeline(modelist);
	modelist = next;
    }
}


/* Prints a linked list of FbModeline structures to stdout.
   Intended primarily for debugging. */
void FB_PrintModeDB(FbModeline *modelist)
{
    FbModeline *pos;

    pos = modelist;

    while (pos != NULL) {
	printf("%s: %ix%i, %i bits, %i dotclock, %s, %s, %s, %s, %s, %s\n",
	       pos->name,
	       pos->xres, pos->yres,
	       pos->depth,
	       pos->dotclock,
	       (pos->hsync ? "hsync high" : "hsync low"),
	       (pos->vsync ? "vsync high" : "vsync low"),
	       (pos->csync ? "csync high" : "csync low"),
	       (pos->extsync ? "external sync" : "no external sync"),
	       (pos->doublescan ? "doublescan" : "single scan"),
	       (pos->laced ? "interlaced" : "non-interlaced"));
	pos = pos->next;
    }
}
