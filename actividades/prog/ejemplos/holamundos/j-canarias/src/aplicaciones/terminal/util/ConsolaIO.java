package terminal.util;

import java.io.*;


/*
 * @author David Vargas Ruiz
 * @version 1.0.0
 * Esta clase hace de interfaz con la consola
 */
public class ConsolaIO {

    /**
     * Método readLine()
     */
    public static String readLine() {
        BufferedReader br;   //Necesario para la lectura readLine
        br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return br.readLine();
        } catch (Exception e) {
            System.err.println("ERROR Consola.readLine():" + e);
        }
        return null;
    }

    /**
     * Método println()
     */
    public static void println(String pTexto) {
        System.out.println(pTexto);
    }

    /**
     * Método print
     * @param pTexto Texto que se quiere imprimir en la consola
     */
    public static void print(String pTexto) {
        System.out.print(pTexto);
    }
    
    /**
     * Muestra en consola una línea construida con <b>simbolo</b>.
     * @param simbolo Carácter utilizado para dibujar la línea
     * @param size Tamaño de la línea
     */
    public static void printlnSimbolo(char simbolo,int size) {
        char c[]= new char[size];
        for(int i=0;i<size;i++) c[i]=simbolo;
        System.out.println(new String(c));
    }
}
