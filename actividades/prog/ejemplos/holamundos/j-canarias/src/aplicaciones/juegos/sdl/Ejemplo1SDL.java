/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos.sdl;

import sdljava.SDLMain;
import sdljava.video.SDLVideo;
import sdljava.video.SDLSurface;
import sdljava.SDLVersion;

/**
 *
 * @author david
 */
public class Ejemplo1SDL {
    public static final String APPS="Ejemplo SDL 1";
    //Atributos
    SDLSurface pantalla;

    public void iniciarSDL() {
        try {
            SDLMain.init(SDLMain.SDL_INIT_VIDEO);

            pantalla = SDLVideo.setVideoMode(640, 480, 16, SDLVideo.SDL_SWSURFACE);
            SDLVideo.wmSetCaption(APPS, APPS);
        } catch (Exception e) {
            System.err.println("[Error] " + e);
        
        }
    }

    public void pararSDL() {
        SDLMain.quit();
    }
    
    public void pararSDLconEspera(int segundos) {
        try { Thread.sleep(segundos*1000);}
        catch (Exception e) {System.err.println("[Error] "+e);}
        this.pararSDL();
    }

    public void mostrarVersionSDL() {
        SDLVersion v;
        v = SDLMain.getSDLVersion();
        System.out.println("SDLVersion = " + v.getMajor() + "." + v.getMinor() + "." + v.getPatch());
    }

    public void leerTeclado() {
        //sdljava.x.swig.SWIG_SDLEventJNI t = new sdljava.x.swig.SWIG_SDLEventJNI();
        for(int i=0;i<10000;i++) {
            
        }
    }
    public static void main(String args[]) {
        Ejemplo1SDL s = new Ejemplo1SDL();
        s.iniciarSDL();
        s.mostrarVersionSDL();
        s.pararSDLconEspera(1);
    }
}
