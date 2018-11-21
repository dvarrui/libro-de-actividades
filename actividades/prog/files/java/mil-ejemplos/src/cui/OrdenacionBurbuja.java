
package cui;

/**
 *
 * @author david
 */
public class OrdenacionBurbuja {
    static int operaciones=0;
    public static int ordenar(int array[]) {
        operaciones=0;
        if (array.length<=1) return operaciones;
        
        for(int siguienteAlUltimo = array.length-2; siguienteAlUltimo>=0;siguienteAlUltimo--)
            for(int indice=0;indice<=siguienteAlUltimo;indice++) 
                compararEIntercambiar(array,indice);
        return operaciones;
    }
    
    private static void intercambiar(int[] array, int indice) {
        int temporal = array[indice];
        array[indice]= array[indice+1];
        array[indice+1]= temporal;
    }
    
    private static void compararEIntercambiar(int[] array, int indice) {
        if (array[indice]>array[indice+1])
            intercambiar(array,indice);
        operaciones++;
    }
}
