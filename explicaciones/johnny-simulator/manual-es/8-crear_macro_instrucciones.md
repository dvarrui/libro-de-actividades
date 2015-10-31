
[INDICE](./README.md)

#8. Creating your own Macro Instructions
In order to create a new macro instruction (or to change an existing one) first an OP-Code
and a mnemonic must be chosen. The latter can be chosen in the pull-down menu later on
when RAM content is changed.

![Load save Micro](./imagen/8-load_save_micro.png)

Then the Record button is pressed:

![Pull down](./imagen/8-pull_down.png)

The respective part of the micro code is set to zero at first. By pressing the buttons of the
respective micro instructions, the sequence is recorded. The recording is indicated by a
blinking part of the user interface. Pressing the Record button again stops the recording.
The new micro code can be saved to HD and opened again by

Saving a micro code creates two files: the .mpc file contains the micro code itself, the .nam
file the Mmnemonics of the instructions.
