#ifndef SCRIPTING_H
#define SCRIPTING_H

#include "gamedefs.h"

void InitScripting(void);
void CleanupScripting(void);
int LoadGameScript(char *filename);
int RunGameScript(void);

#endif
