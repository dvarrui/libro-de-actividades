#ifndef FRAMES_H_
#define FRAMES_H_

#include <SDL/SDL.h>

//Frames contiene todos las imágenes del juego
class Frames {
public:
	int numframes;
	SDL_Surface *img[10];

private:
	SDL_Surface *prueba;
	
public:	
	Frames();
	void unload();

private:
	void load_all_frames();
	void load_frame(const char *path);

};

#endif /* FRAMES_H_ */
