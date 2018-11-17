#ifndef FRAMES_H_
#define FRAMES_H_

//Frame representa un frame independiente de un sprite
class Frames {
public:
	int numframes;
	SDL_Surface *img[10];

public:	
	Frames();
	void unload();

private:
	void load(const char *path);

};

#endif /* FRAMES_H_ */
