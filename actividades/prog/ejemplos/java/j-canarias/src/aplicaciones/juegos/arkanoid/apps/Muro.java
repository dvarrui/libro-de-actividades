
package juegos.arkanoid.apps;

/**
 *
 * @author david
 */
public class Muro extends Sprite {
    //Atributos
    private int resistencia;

    public int getResistencia() {
        return this.resistencia;
    }

    public Muro() {
        //if (this.fichero.isEmpty()) {fichero=Juego.DIR_IMG+"pared_gris.bmp";}
        super();
        resistencia = 1;
    }

    public String toCadena() {
        String s;
        s = "Muro" + Sprite.SEP + fichero;
        s = s + Sprite.SEP + x + Sprite.SEP + y;
        s = s + Sprite.SEP + resistencia;
        return s;
    }
    
    public void fromCadena(String cadena) {
        String items[] = cadena.trim().split(Sprite.SEP);
        //items[0] es igual a self.class.to_s
        x = Integer.parseInt(items[2]);
        y = Integer.parseInt(items[3]);
        resistencia = Integer.parseInt(items[4]);
        cargarImagen(items[1]);
    }
   
    public static Muro newFromCadena(String cadena) {
        Muro m = new Muro();
        m.fromCadena(cadena);
        return m;
    }
    
    protected void absorverImpacto() {
        if (resistencia>0) { resistencia-=1;}
        if (resistencia==0) { visible=false;}
    }    
}
