/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package polinomio;

/**
 *
 * @author Profesor
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Programa principal polinomio");
        
        Polinomio p = new Polinomio(3);
        p.setCoeficiente(0, 3);
        p.setCoeficiente(1, 0);
        p.setCoeficiente(2, -2);
        System.out.println("Polinomio.toCadena : "+p.toCadena());
        System.out.println("Polinomio.info     : "+p.info());
        
        p.setCoeficientes("4,-5,6,-7");
        System.out.println("Polinomio.toCadena : "+p.toCadena());
        System.out.println("Polinomio.info     : "+p.info());
    }

}
