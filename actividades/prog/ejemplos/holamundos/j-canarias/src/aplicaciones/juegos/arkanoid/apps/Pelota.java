

package juegos.arkanoid.apps;

/**
 * <p>Esta clase representa a un objeto que se mueve por la escena y
 * rebota contra los demás obstaćulos.</p>
 * 
 * @author David Vargas <dvargas@canarias.org>
 * @version 0.1.0
 */
public class Pelota extends Sprite {
    
    public String toCadena() {
        String s;
        s="Pelota"+Sprite.SEP+fichero;
        s=s+Sprite.SEP+x+Sprite.SEP+y+Sprite.SEP+dx+Sprite.SEP+dy;
        s=s+Sprite.SEP+margen;
        return s;
    }

    public void fromCadena(String cadena) {
        String items[]=cadena.trim().split(Sprite.SEP);
        //items[0] es igual a self.class.to_s
        this.cargarImagen(items[1]);
        x=Integer.parseInt(items[2]);
        y=Integer.parseInt(items[3]);
        dx=Integer.parseInt(items[4]);
        dy=Integer.parseInt(items[5]);
        margen=Integer.parseInt(items[6]);
    }
    
    /**
     * <p>Crea un nuevo objeto del tipo <b>Pelota</b>, a partir del
     * parámetro recibido.</p>
     * @param cadena Contiene los atributos para crear el objeto.
     * @return Devuelve un objeto <b>Pelota</b>, creado a partir de la cadena.
     */
    public static Pelota newFromCadena(String cadena) {
        Pelota p = new Pelota();
        p.fromCadena(cadena);
        return p;
    }
}
