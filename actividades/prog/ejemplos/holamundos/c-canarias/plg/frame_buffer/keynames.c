/* More advanced keycode viewer that identifies the keys it encounters. */

#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <sys/ioctl.h>
#include <linux/keyboard.h>
#include <linux/kd.h>
#include <sys/types.h>

/* Our key-mapping table. This will contain printable characters
   for some keys.  */
char keymap[NR_KEYS];

/* We'll assign names to certain keys. */
char *keynames[NR_KEYS];

/* Locates the arrow keys and the Escape key in the kernel's keymaps.
   Fills in the appropriate globals. */
void init_keymap(int kb)
{
    struct kbentry entry;
    int keycode;

    for (keycode = 0; keycode < NR_KEYS; keycode++) {
			
	keymap[keycode] = ' ';
	keynames[keycode] = "(unknown)";
		
	/* Look up this key. If the lookup fails, ignore.
	   If it succeeds, KVAL(entry.kb_value) will be the
	   8-bit representation of the character the kernel
	   has mapped to this keycode. */
	entry.kb_table = 0;
	entry.kb_index = keycode;
	if (ioctl(kb, KDGKBENT, &entry) != 0) continue;
			
	/* Is this a printable character?
	   NOTE: we do not handle Unicode translation here.
	   See the SDL source (SDL_fbevents.c) for
	   an example of how this can be done.

	   Add in KT_LATIN and KT_ASCII if you want a wider
	   range of characters. They're omitted here because
	   some characters do not print cleanly. */
	if (KTYP(entry.kb_value) == KT_LETTER) {
	    keymap[keycode] = KVAL(entry.kb_value);
	    keynames[keycode] = "(letter)";
	}

	/* Since the arrow keys are useful in games, we'll pick
	   them out of the swarm. While we're at it, we'll grab
	   Enter, Ctrl, and Alt. */
	if (entry.kb_value == K_LEFT) keynames[keycode] = "Left arrow";
	if (entry.kb_value == K_RIGHT) keynames[keycode] = "Right arrow";
	if (entry.kb_value == K_DOWN) keynames[keycode] = "Down arrow";
	if (entry.kb_value == K_UP) keynames[keycode] = "Up arrow";
	if (entry.kb_value == K_ENTER) keynames[keycode] = "Enter";
	if (entry.kb_value == K_ALT) keynames[keycode] = "Left Alt";
	if (entry.kb_value == K_ALTGR) keynames[keycode] = "Right Alt";

    }

    /* Manually plug in keys that the kernel doesn't normally map correctly. */
    keynames[29] = "Left Control";
    keynames[97] = "Right Control";
    keynames[125] = "Left Linux key";   /* usually mislabeled with */
    keynames[126] = "Right Linux key";  /* a Windows logo. */
    keynames[127] = "Application key";
}

/* Checks whether or not the given file descriptor is associated with a
   local keyboard.
   Returns 1 if it is, 0 if not (or if something prevented us from
   checking). */
int is_keyboard(int fd)
{
    int data;

    /* See if the keyboard driver groks this file descriptor. */
    data = 0;
    if (ioctl(fd, KDGKBTYPE, &data) != 0)
	return 0;
	
    /* In current versions of Linux, the keyboard driver always answers
       KB_101 to keyboard type queries. It's probably sufficient to just
       check whether the above ioctl succeeds or fails. */
    if (data == KB_84) {
	printf("84-key keyboard found.\n");
	return 1;
    } else if (data == KB_101) {
	printf("101-key keyboard found.\n");
	return 1;
    }

    /* Sorry, this didn't check out. */
    return 0;
}


int main()
{
    struct termios old_term, new_term;
    int kb = -1; /* keyboard file descriptor */
    char *files_to_try[] = {"/dev/tty", "/dev/console", NULL};
    int old_mode = -1;
    int i;

    /* First we need to find a file descriptor that represents the
       system's keyboard. This should be /dev/tty, /dev/console, stdin,
       stdout, or stderr. We'll try them in that order. If none are
       acceptable, we're probably not being run from a VT. */
    for (i = 0; files_to_try[i] != NULL; i++) {

	/* Try to open the file. */
	kb = open(files_to_try[i], O_RDONLY);
	if (kb < 0) continue;

	/* See if this is valid for our purposes. */
	if (is_keyboard(kb)) {
	    printf("Using keyboard on %s.\n", files_to_try[i]);
	    break;
	}
		
	close(kb);
    }

    /* If those didn't work, not all is lost. We can try the 3 standard
       file descriptors, in hopes that one of them might point to a
       console. This is not especially likely. */
    if (files_to_try[i] == NULL) {	
	for (kb = 0; kb < 3; kb++) {
	    if (is_keyboard(i)) break;
	}

	printf("Unable to find a file descriptor associated with the "\
	       "keyboard.\n"\
	       "Perhaps you're not using a virtual terminal?\n");
	return 1;

    }

    /* Find the keyboard's current mode so we can restore it later. */
    if (ioctl(kb, KDGKBMODE, &old_mode) != 0) {
	printf("Unable to query keyboard mode.\n");
	goto error;
    }

    /* Adjust the terminal's settings. In particular, disable echoing,
       signal generation, and line buffering. Any of these could cause
       trouble. Save the old settings first. */
    if (tcgetattr(kb, &old_term) != 0) {
	printf("Unable to query terminal settings.\n");
	goto error;
    }

    new_term = old_term;
    new_term.c_iflag = 0;
    new_term.c_lflag &= ~(ECHO | ICANON | ISIG);

    /* TCSAFLUSH discards unread input before making the change.
       A good idea. */
    if (tcsetattr(kb, TCSAFLUSH, &new_term) != 0) {
	printf("Unable to change terminal settings.\n");
    }

    /* Put the keyboard in mediumraw mode. */
    if (ioctl(kb, KDSKBMODE, K_MEDIUMRAW) != 0) {
	printf("Unable to set mediumraw mode.\n");
	goto error;
    }

    /* Discover the keymap. */
    init_keymap(kb);

    printf("Reading keycodes. Press Escape (keycode 1) to exit.\n");

    for (;;) {
	unsigned char data;

	if (read(kb, &data, 1) < 1) {
	    printf("Unable to read data. Trying to exit nicely.\n");
	    goto error;
	}

	/* Print the keycode. The top bit is the pressed/released flag,
	   and the lower seven are the keycode. */
	printf("%s: %2Xh (%3i)  Char: %c  Special: %s\n",
	       (data & 0x80) ? "Released" : " Pressed",
	       (unsigned int)data & 0x7F,
	       (unsigned int)data & 0x7F,
	       keymap[data & 0x7F],
	       keynames[data & 0x7F]);

	if ((data & 0x7F) == 1) {
	    printf("Escape pressed.\n");
	    break;
	}
    }

    /* Shut down nicely. */
    printf("Exiting normally.\n");

    ioctl(kb, KDSKBMODE, old_mode);
    tcsetattr(kb, 0, &old_term);

    if (kb > 3)
	close(kb);

    return 0;

 error:
    printf("Cleaning up.\n");
    fflush(stdout);

    /* Restore the previous mode. Users hate it when they can't use
       the keyboard. */
    if (old_mode != -1) {
	ioctl(kb, KDSKBMODE, old_mode);
	tcsetattr(kb, 0, &old_term);
    }

    /* Only bother closing the keyboard fd if it's not stdin, stdout,
       or stderr. */
    if (kb > 3)
	close(kb);
		
    return 1;
}
