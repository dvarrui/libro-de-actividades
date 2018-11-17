
package juegos.arkanoid.apps;

import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import sdljava.video.SDLRect;

/**
 *
 * @author David Vargas <dvargas@canarias.org>
 * @version 0.1.0
 */
public class Sprite {

    public static final int NORTE = 0;
    public static final int SUR = 1;
    public static final int ESTE = 2;
    public static final int OESTE = 3;
    public static final int X=4;
    public static final int Y=5;
    public static final int XY=6;
    public static final String SEP=":";
    
    //Atributos de la clase
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int margen;
    protected String fichero;
    protected boolean visible;
    
    private SDLSurface imagen;
    private SDLRect posicion;

    public String getFichero() {
        return this.fichero;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Sprite() {
        this.inicializar();
    }

    private void inicializar() {
        //Atributos de la apariencia
        //this.fichero = Juego.DIR_IMG + "barra.bmp";
        //cargarImagen(fichero);

        //TODO: Las coordenadas son float, luego se pasan a enteros
        this.x = 0;
        this.y = 0;
        this.dx = 0;
        this.dy = 0;
        this.margen = 0;
        this.visible = false;
    }

    protected void cargarImagen(String fichero) {
        try {
            this.fichero = fichero;
            imagen = SDLVideo.loadBMP(Juego.DIR_IMG+fichero);
            imagen.setColorKey(SDLVideo.SDL_SRCCOLORKEY, 0);
            //imagen=imagen.displayFormat();
            visible=true;
            posicion = new SDLRect(x,y,imagen.getWidth(),imagen.getHeight());
        } catch (Exception e) {
            System.err.println("[Error: fichero=]" + Juego.DIR_IMG+fichero);
            System.err.println("[Error: Sprite.cargarImagen] " + e);
        }
    }
    
    public int getAncho() {
        return this.imagen.getWidth();
    }
    
    public int getAlto() {
        return this.imagen.getHeight();
    }
    
    public void pintar(SDLSurface pantalla) {
        //pantalla.blitSurface(this.imagen, 0,0,this.imagen.getWidth(), arg2);
        //SDL.blitSurface(@imagen,0,0,@imagen.w,@imagen.h,pantalla,@x,@y)
        //TODO: función blit SDL para revisar.
        try {            
            posicion.x=x;
            posicion.y=y;
            imagen.blitSurface(pantalla, posicion);
        } catch (Exception e) {
            System.err.println("Error: "+e);
        }        
    }
    
    public void mover() {
        this.x=this.x+this.dx;
        this.y=this.y+this.dy;
        if (oeste()<Juego.OESTE || este()>Juego.ESTE) cambiarDireccion(Sprite.X); 
        if (norte()<Juego.NORTE || sur()>Juego.SUR) cambiarDireccion(Sprite.Y); 
        posicion.x=x;
        posicion.y=y;
    }
    
    public boolean seMueve() {
        if (this.dx==0 && this.dy==0) return false;
        return true;
    }

    public void cambiarDireccion(int opcion) {
        if (this.seMueve()) {
            if (opcion==Sprite.X) {dx=dx*-1;}
            else if (opcion==Sprite.Y) {dy=dy*-1;}
            else if (opcion==Sprite.XY) {
                dx=dx*-1;
                dy=dy*-1;
            }
        }
    }
    
    public int norte() {return y+margen;}
    public int sur() {return y+imagen.getHeight()-margen;}
    public int este() { return x+imagen.getWidth()-margen;}
    public int oeste() { return x+margen;}

    public boolean colisionaCon(Sprite otro) {
        //Comprobar si hay colisión
        boolean haySolape = otro.este()>=oeste() && otro.oeste()<=este() && otro.sur()>=norte() && otro.norte()<=sur();
        if (!haySolape) return false;
        if (otro instanceof Muro) {
            //Muro m = (Muro) otro;
            //m.absorverImpacto();
        }
        //Existe colisión.
        //Ahora hay que determinar los cambios de dirección
        int margen_x,contacto_x;
        int margen_y,contacto_y;
        
        margen_x = 0;
        if (oeste()<otro.oeste()) { margen_x=margen_x+(otro.oeste()-oeste());}
        if (este()>otro.este()) { margen_x=margen_x+(este()-otro.este());}
        contacto_x=this.getAncho()-margen_x;
        
        margen_y = 0;
        if (norte()<otro.norte()) { margen_y=margen_y+(otro.norte()-norte());}
        if (sur()>otro.sur()) { margen_y=margen_y+(sur()-otro.sur());}
        contacto_y=this.getAlto()-margen_y;

        //print fichero, ":",contacto_x, ",",contacto_y,"\n"
        //sleep 10
        if (contacto_x>contacto_y) {cambiarDireccion(Sprite.Y);}
        else if (contacto_x<contacto_y) {cambiarDireccion(Sprite.X);}
        else { cambiarDireccion(Sprite.XY);}

        return true;
    }
}
