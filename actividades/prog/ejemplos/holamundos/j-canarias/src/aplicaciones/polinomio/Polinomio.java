/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomio;

/** 
 *
 * @author Profesor
 */
public class Polinomio {
    //Atributos
    private int coeficientes[];

    public Polinomio(int grado) {
        this.inicializar(grado);
    }

    private void inicializar(int grado) {
        this.coeficientes = new int[grado];
        for (int i = 0; i < grado; i++) {
            this.coeficientes[i] = 0;
        }
    }

    /**
     * <p>Este m con el polinomio completo
     * 
     * @return Devuelve un objeto <b>String</b>
     */
    public String toCadena() {
        String s = "";

        for (int i = coeficientes.length - 1; i >= 1; i--) {
            if (coeficientes[i] != 0) {
                if (coeficientes[i] > 0 && i != coeficientes.length - 1) {
                    s += " +";
                }
                s += " " + coeficientes[i];
                if (i == 1) {
                    s += "x";
                } else {
                    s += "x^" + i;
                }
            }
        }
        if (coeficientes[0] != 0) {
            s += " " + coeficientes[0];
        }
        return s;
    }

    /**
     * <p>Este método se usa para modificar los coeficientes de un objeto de
     * tipo <b>Polinomio</b> de forma individual.</p>
     * 
     * @param posicion Es la posición del coeficiente que quiero modificar.
     * @param valor Es el valor que quiero poner al coeficiente.
     */
    public void setCoeficiente(int posicion, int valor) {
        this.coeficientes[posicion] = valor;
    }

    public void setCoeficientes(String valores) {
        String s[];
        s = valores.split(",");
        this.inicializar(s.length);
        for (int i = 0; i < s.length; i++) {
            coeficientes[i] = Integer.parseInt(s[i]);
        }
    }

    public String info() {
        String s = "{";
        for (int i = 0; i < coeficientes.length; i++) {
            s = s + coeficientes[i] + ",";
        }
        s = s + "}";
        return s;
    }

    public int getGrado() {
        return this.coeficientes.length;
    }

    public boolean esIgual(Polinomio p) {
        boolean sonIguales = false;
        if (this.toCadena().equals(p.toCadena())) {
            sonIguales=true;
        }
        return sonIguales;
    }
}
