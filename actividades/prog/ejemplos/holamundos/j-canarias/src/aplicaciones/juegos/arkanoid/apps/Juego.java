package juegos.arkanoid.apps;

import sdljava.SDLMain;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

import sdljava.event.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author david
 */
public class Juego {

    //public static final String DIR_DATOS = "juegos/arkanoid/datos/";
    public static final String DIR_DATOS = "/home/david/workspace/j-canarias/src/aplicaciones/juegos/arkanoid/datos/";
    //public static final String DIR_IMG = "juegos/arkanoid/img/";
    public static final String DIR_IMG = "/home/david/workspace/j-canarias/src/aplicaciones/juegos/arkanoid/img/";
    public static final int NORTE = 0;
    public static final int SUR = 480;
    public static final int OESTE = 0;
    public static final int ESTE = 640;
    public static final int PANTALLA_ANCHO = 640;
    public static final int PANTALLA_ALTO = 480;
    private SDLSurface pantalla;
    private SDLSurface fondo;
    private ArrayList<Sprite> moviles;
    private ArrayList<Sprite> estaticos;
    private Barra jugador;

    public Juego() {
        //Inicializar los atributos de la clase
        moviles = new ArrayList<Sprite>();
        estaticos = new ArrayList<Sprite>();
        //Inicializar SDL
        try {
            SDLMain.init(SDLMain.SDL_INIT_VIDEO);

            //pantalla = SDLVideo.setVideoMode(PANTALLA_ANCHO, PANTALLA_ALTO, 16, SDLVideo.SDL_DOUBLEBUF + SDLVideo.SDL_HWSURFACE + SDLVideo.SDL_HWACCEL);
            pantalla = SDLVideo.setVideoMode(PANTALLA_ANCHO, PANTALLA_ALTO, 16, SDLVideo.SDL_DOUBLEBUF + SDLVideo.SDL_SWSURFACE + SDLVideo.SDL_RLEACCEL);
            SDLVideo.wmSetCaption("Arkanoid 0.1.1 (Java)", "Arkanoid Java");
            //TODO:fondo
            fondo = new SDLSurface(pantalla.getSwigSurface());


        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    //getter/setter
    public SDLSurface getPantalla() {
        return this.pantalla;
    }

    public ArrayList<Sprite> getEstaticos() {
        return this.estaticos;
    }

    public ArrayList<Sprite> getMoviles() {
        return this.moviles;
    }

    /**
     * <p>Cargar datos desde un fichero de configuración ASCII.</p>
     * @param fichero Nombre del fichero de configuración.
     */
    public void cargarDatos(String fichero) {
        if (fichero.isEmpty()) {
            fichero = Juego.DIR_DATOS + "partida1.txt";
        }
        try {
            String s;
            BufferedReader f = new BufferedReader(new FileReader(fichero));
            while ((s = f.readLine()) != null) {
                if (s.startsWith("Muro")) {
                    //Leer Muro del fichero de configuración
                    Muro m = Muro.newFromCadena(s);
                    this.estaticos.add(m);
                } else if (s.startsWith("Pelota")) {
                    //Leer Pelota del fichero de configuración
                    Pelota p = Pelota.newFromCadena(s);
                    this.moviles.add(p);
                } else if (s.startsWith("Barra")) {
                    Barra b = Barra.newFromCadena(s);
                    this.estaticos.add(b);
                    this.jugador = b;
                }
            }
        } catch (Exception e) {
            System.err.println("[Error] " + e);
        }
    }

    /**
     * Iniciar eljuego.
     */
    public void iniciar() {
        this.iniciarMainLoop();
    }

    private void iniciarMainLoop() {
        boolean continuar = true;
        SDLEvent evento;

        try {
            boolean mover_izq;
            boolean mover_der;
            while (continuar) {
                //Detectar los eventos 
                evento = SDLEvent.pollEvent();
                mover_izq=mover_der=false;
                while (evento != null) {
                    if (evento.getType() == SDLEvent.SDL_QUIT) {
                        continuar = false;
                    }
                    if (evento.getType() == SDLEvent.SDL_KEYDOWN) {
                        //Se comprueba si se ha pulsado escape
                        SDLKeyboardEvent k = (SDLKeyboardEvent) evento;
                        if (k.getSym() == SDLKey.SDLK_ESCAPE) {
                            continuar = false;
                        } else if (k.getSym()==SDLKey.SDLK_LEFT) {
                            mover_izq=true;
                        } else if (k.getSym()==SDLKey.SDLK_RIGHT) {
                            mover_der=true;
                        }
                    }
                    evento = SDLEvent.pollEvent();
                }
                if (mover_izq) { jugador.mover(Barra.IZQ);}
                if (mover_der) { jugador.mover(Barra.DER);}
                //Realizar cambios en el micromundo
                this.moverObjetos();
                //Pintar el micromundo
                this.pintarMicroMundo();
            }
            SDLMain.quit();

        } catch (Exception e) {
            System.err.println("[Error iniciarMainLoop] " + e);
        }
    }

    private void moverObjetos() {
        for (Sprite s : this.getMoviles()) {
            s.mover();
        }
        this.detectarColisiones();
    }

    private void detectarColisiones() {
        java.util.Iterator<Sprite> i = this.getMoviles().iterator();
        while (i.hasNext()) {
            Sprite s1 = i.next();
            java.util.Iterator<Sprite> j = this.getEstaticos().iterator();
            while (j.hasNext()) {
                Sprite s2 = j.next();
                s1.colisionaCon(s2);
                if (!s2.isVisible()) {
                    j.remove();
                }
            }
        }
    }

    private void pintarMicroMundo() {
        //SDLRect r = new SDLRect
        try {
            pantalla.fillRect(0);
            for (Sprite s : this.getEstaticos()) {
                s.pintar(pantalla);
            }
            for (Sprite s : this.getMoviles()) {
                s.pintar(pantalla);
            }
            //SDL::Key.scan
            //pantalla.updateRect();
            pantalla.flip();
        } catch (Exception e) {
            System.err.println("[Error] " + e);
        }

    }
}

