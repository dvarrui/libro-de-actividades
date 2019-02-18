/*
 * (1) Crea una instancia del juego
 * (2) Carga una configuración concreta en el juego
 * (3) Inicia el bucle principal del juego
 */

package juegos.arkanoid;

import juegos.arkanoid.apps.*;

/**
 *
 * @author David Vargas <dvargas@canarias.org>
 */
public class Principal {
    public static void main(String args[]) {
        Juego j = new Juego();
        j.cargarDatos(Juego.DIR_DATOS+"partida1.txt");
        j.iniciar();
    }
}
