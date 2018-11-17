package cui.tiposdedatos.cadenas;

/**
 * Esta clase contiene una seria de métodos para trabajar con String
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060218
 * 
 */
public class Cadena {

    public static void main(String[] args) {
        String texto = "David Vargas Ruiz";
        System.out.println("Cadena     :" + texto);
        System.out.println("Rotar izq   1  :" + Cadena.rotarIzquierda2(texto, 1));
        System.out.println("Rotar izq   3  :" + Cadena.rotarIzquierda2(texto, 3));
        System.out.println("Rotar izq - 3  :" + Cadena.rotarIzquierda2(texto, -3));
        System.out.println("Rotar izq  20  :" + Cadena.rotarIzquierda2(texto, 20));
        System.out.println("Rotar der   1  :" + Cadena.rotarDerecha2(texto, 1));
        System.out.println("Rotar der   3  :" + Cadena.rotarDerecha2(texto, 3));
        System.out.println("Rotar der - 3  :" + Cadena.rotarDerecha2(texto, -3));
        System.out.println("Rotar der  20  :" + Cadena.rotarDerecha2(texto, 20));
    }

    public static String rotarDerecha2(String entrada, int index) {
        return rotarIzquierda2(entrada, index * (-1));
    }

    /**
     * Rotar una cadena a la izquierda usando substrigs
     * 
     * @param entrada
     * @return Cadena rotada
     */
    public static String rotarIzquierda2(String entrada, int index) {
        String salida = null;

        // -------------------------------------
        // Validar la entrada de datos al método
        // -------------------------------------

        // Reajustamos index para evitar ciclos completos en las rotaciones
        while (index > entrada.length()) {
            index = index - entrada.length();
        }
        while (index < 0) {
            index = index + entrada.length();
        }
        // Ahora index está entre 1 y entrada.length

        // Si la entrada es null o "" la devolvemos tal cual
        // además si la cadena es una letra se devuelve tal cual
        if (entrada.length() == 0 || entrada.length() == 1) {
            return entrada;
        }

        // Si tenemos justo un ciclo completto de rotación devolvemos la entrada
        if (entrada.length() == index) {
            return entrada;
        }

        // --------------------
        // Ejecutar la rotación
        // --------------------
        salida = entrada.substring(index) + entrada.substring(0, index);
        return salida;
    }
    String cadena;

    /**
     * Muestra un Banner por la consola
     * 
     * @param texto
     *            Texto que se quiere mostrar en el banner
     * @param letra
     *            Es el carácter que se va a utilizar en el recuadro
     * @param espacio
     *            Es la cantidad de espacio estre el texto y el recuadro
     */
    public void banner(String texto, String letra, int espacio) {
        // 1
        for (int i = 0; i < (texto.length() + espacio * 2 + 2); i++) {
            System.out.print(letra);
        }
        System.out.println();
        // 2
        for (int i = 0; i < espacio; i++) {
            System.out.print(letra);
            for (int j = 0; j < (texto.length() + espacio * 2); j++) {
                System.out.print(" ");
            }
            System.out.println(letra);
        }
        // 3
        System.out.print(letra);
        for (int i = 0; i < espacio; i++) {
            System.out.print(" ");
        }
        System.out.print(texto);
        for (int i = 0; i < espacio; i++) {
            System.out.print(" ");
        }
        System.out.println(letra);
        // 4
        for (int i = 0; i < espacio; i++) {
            System.out.print(letra);
            for (int j = 0; j < (texto.length() + espacio * 2); j++) {
                System.out.print(" ");
            }
            System.out.println(letra);
        }
        // 5
        for (int i = 0; i < (texto.length() + espacio * 2 + 2); i++) {
            System.out.print(letra);
        }
        System.out.println();
    }

    /**
     * Realiza una criptografía tipo César
     * 
     * @param p
     *            Texto que se quiere criptografiar
     * @return Resultado criptografiado
     */
    public String criptografiarCesar(String p) {
        return this.criptografiarDesplazamiento(p, 3);
    }

    /**
     * Realiza una criptografía por desplazamiento utilizando variables tipo
     * String
     * 
     * @param p
     *            Texto que se quiere criptografiar
     * @param desp
     *            Cantidad de desplazamiento a utilizar
     * @return Resultado criptografiado
     */
    public String criptografiarDesplazamiento(String p, int desp) {
        String abecedario = new String("abcdefghijklmnñopqrstuvwxyz");
        String r = new String("");
        int posicion;

        while (desp > abecedario.length()) {
            desp = desp - abecedario.length();
        }
        while (desp < 0) {
            desp = desp + abecedario.length();
        }

        abecedario = abecedario.concat(abecedario);
        abecedario = abecedario.concat(abecedario.toUpperCase());
        for (int i = 0; i < p.length(); i++) {
            posicion = abecedario.indexOf(p.substring(i, i + 1));
            if (posicion < 0) {
                r = r.concat(p.substring(i, i + 1));
            } else {
                r = r.concat(abecedario.substring(posicion + desp, posicion + desp + 1));
            }
        }
        return r;
    }

    /**
     * Realiza una criptografía por desplazamiento usando variable tipo
     * char().Esto es, vectores de tipo char.
     * 
     * @param p
     *            Texto de entrada
     * @param desp
     *            Cantidad de desplazamiento
     * @return Devuelve un
     *         <p>
     *         String
     *         <p>
     *         criptografiado
     */
    public String criptografiarDesplazamiento2(String p, int desp) {
        char[] abecedarioInicial = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
        char[] AbecedarioInicial = "abcdefghijklmnñopqrstuvwxyz".toUpperCase().toCharArray();
        char[] abecedarioCripto;
        char[] AbecedarioCripto;
        char[] entrada = p.toCharArray();
        char[] salida = p.toCharArray();
        int posicion;

        abecedarioCripto = rotarIzquierda(abecedarioInicial, desp);
        AbecedarioCripto = rotarIzquierda(AbecedarioInicial, desp);

        for (int i = 0; i < entrada.length; i++) {
            // Probamos con las minúsculas
            posicion = devolverPosicion(abecedarioInicial, entrada[i]);
            if (posicion >= 0) {
                salida[i] = abecedarioCripto[posicion];
            } else {
                // Probamos con las mayúsculas
                posicion = devolverPosicion(AbecedarioInicial, entrada[i]);
                if (posicion >= 0) {
                    salida[i] = AbecedarioCripto[posicion];
                }
            }
        }
        return new String(salida);
    }

    /**
     * Localizar posición de un char dentro de un array char()
     * 
     * @param entrada
     *            Es el vector de char
     * @param letra
     *            Es el char a buscar
     * @return Devuelve la posición
     */
    public int devolverPosicion(char[] entrada, char letra) {
        for (int i = 0; i < entrada.length; i++) {
            if (entrada[i] == letra) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Realiza la inversión de una cadena
     * 
     * @param p
     *            Entrada
     * @return Salida invertida
     */
    public String invertirCadena(String p) {
        String r = new String("");

        for (int i = p.length(); i > 0; i--) {
            r = r.concat(p.substring(i - 1, i));
        }
        return r;
    }

    /**
     * Rotar un vector de char a la izquierda una posición
     * 
     * @param Entrada
     *            del vector char
     * @return Vector char rotado
     */
    public char[] rotarIzquierda(char[] entrada) {
        char[] salida = (char[]) entrada.clone();
        char letra = salida[0];

        for (int i = 0; i < salida.length - 1; i++) {
            salida[i] = salida[i + 1];
        }
        salida[salida.length - 1] = letra;
        return salida;
    }

    /**
     * Rotar un vector de char a la izquierda varias posiciones
     * 
     * @param Entrada
     *            del vector char
     * @param desp
     *            Número de posiciones a rotar
     * @return Vector char rotado
     */
    public char[] rotarIzquierda(char[] entrada, int desp) {
        int i = 0;
        char[] salida = (char[]) entrada.clone();
        if (desp > 0) {
            while (i++ < desp) {
                salida = rotarIzquierda(entrada);
                entrada = (char[]) salida.clone();
            }
        }

        return salida;
    }

    /**
     * Rotar una cadena a la izquierda usando vectores de char
     * 
     * @param entrada
     * @return Cadena rotada
     */
    public char[] rotarIzquierda(String entrada) {
        return rotarIzquierda(entrada.toCharArray());
    }
}
