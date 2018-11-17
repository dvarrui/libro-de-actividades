#ifndef PARTIDA_H_
#define PARTIDA_H_

#define MAXMAP	400

class Pantalla {
private:
	int posx, posy;
	int estado;
	int numframes;
	int cont; //variable auxiliar utilizada para contabilidad lo frames cargados en el sprite
	
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
	void pintar(SDL_Surface * superficie);
	int colision(CSprite sp);
};

#endif /* PARTIDA_H_ */
