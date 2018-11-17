#ifndef CSPRITE_H_
#define CSPRITE_H_

#define	TRUE	1
#define	FALSE	0

//CFrame representa un frame independiente de un sprite
class CFrame {
	public:
	SDL_Surface *img;
	void load(const char *path);
	void unload();
};

//La clase CSprite está formada por un array de frames;
class CSprite {
private:
	int posx, posy;
	int estado;
	int nframes;
	int cont;
	
public:
	CFrame *sprite;
	CSprite(int nf);
	CSprite();
	void finalize(); 
	void addframe(CFrame frame);
	void selframe(int nf);
	void setx(int x) { posx=x; }
	void sety(int y) { posy=y; }
	void addx(int c) { posx+=c; }
	void addy(int c) { posy+=c; }
	int getx() { return posx; }
	int gety() { return posy; }
	int getw() { return sprite[estado].img->w; }
	int geth() { return sprite[estado].img->h; }
	void draw(SDL_Surface * superficie);
	int colision(CSprite sp);
};

#endif /* CSPRITE_H_ */
