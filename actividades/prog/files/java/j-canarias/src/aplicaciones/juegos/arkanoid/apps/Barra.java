
package juegos.arkanoid.apps;

/**
 *
 * @author David Vargas <dvargas@canarias.org>
 * @version 0.1.0
 */
public class Barra extends Sprite {
    public static final int IZQ=-1;
    public static final int DER=+1;
    
    private int desplazamiento;
    
    public Barra() {
        //super(Juego.DIR_IMG+"barra.bmp");
        super();
        desplazamiento=5;
    }
 
    public String toCadena() {
        String s;
        s="Barra"+Sprite.SEP+fichero;
        s=s+Sprite.SEP+x+Sprite.SEP+y+Sprite.SEP+margen;
        return s;
    }

    public void fromCadena(String cadena) {
        String items[] = cadena.trim().split(Sprite.SEP);
        //#items[0] es igual a self.class.to_s

        x=Integer.parseInt(items[2]);
        y=Integer.parseInt(items[3]);
        cargarImagen(items[1]);
        margen=Integer.parseInt(items[4]);
    }
    
    public static Barra newFromCadena(String cadena) {
        Barra b = new Barra();
        b.fromCadena(cadena);
        return b;
    }
    
    public void mover(int sentido) {
        if (sentido==Barra.IZQ) {
            if (x>Juego.OESTE) { x=x-desplazamiento;}
        } else if (sentido==Barra.DER) {
            if ((x+this.getAncho())<Juego.ESTE) { x=x+desplazamiento;}
        }
    }

}
