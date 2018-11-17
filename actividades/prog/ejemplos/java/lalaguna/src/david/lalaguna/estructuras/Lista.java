package david.lalaguna.estructuras;

import java.util.StringTokenizer;

/**
 * @author David Vargas Ruiz
 * @version 20060823
 * 
 * <p>
 * La clase <b>Lista</b> implementa la estructura lista del lenguaje de
 * programación LISP.
 * </p>
 * <p>
 * El carácter especial no se puede usar al comienzo de un átomo. Tiene un
 * significado especial, interno a la clase. Las listas y átomos son estructuras
 * similares a las del lenguaje LISP.
 * </p>
 */
public class Lista implements IEstructura {
	final static String CARACTERCONSTANTE = "#";

	public final static String CARACTERESPECIAL = "_"; // Esta constante se
														// hace pública para
														// EntidadAtomo

	final static int MAX_ATOMOSPORSUBLISTA = 5;

	final static int MAX_SUBLISTAS = 5;

	public static void main(String[] args) {
		Lista l = new Lista();
		l.setLista("(A   (B (C (D))))");
		l.mostrar();
		l.setLista("((((D)C)B)A)");
		l.mostrar();
		l.setLista("(padre (hijo1 hijo2)madre)");
		l.mostrar();
		l.setLista("((hijo1) padre (hijo2))");
		l.mostrar();
		l.setLista("((#_a)(#_b))");
		l.mostrar();
	}

	private String atomos[][];

	private String descError = null;

	private boolean error = false;

	private String lista = null;

	private int num_atomos = 0;

	private int num_sublistas = 0;

	private int profundidad_maxima = 0;

	public Lista() {
		atomos = new String[MAX_SUBLISTAS][MAX_ATOMOSPORSUBLISTA];
	}

	public Object clone() {
		Lista l = new Lista();
		l.setLista(this.getString());
		return l;
	}

	private boolean crearDesdeString(String lista) {

		String str;
		int parentesis_abiertos = 0;
		int puntero_sublista_actual = -1;

		// Comprobar los paréntesis y el carácter especial
		StringTokenizer st = new StringTokenizer(lista, " ()", true);
		while (st.hasMoreElements()) {
			str = (String) st.nextElement();

			if (str.startsWith(CARACTERESPECIAL)) {
				descError = "No se puede usar el carácter " + CARACTERESPECIAL
						+ " al comienzo de un átomo.";
				str = CARACTERCONSTANTE + str;
			}

			if (str.equals("(")) {
				// ----------------
				// ABRIR PARÉNTESIS
				// ----------------
				parentesis_abiertos++;

				if (parentesis_abiertos > profundidad_maxima)
					profundidad_maxima = parentesis_abiertos;
				if (puntero_sublista_actual > -1) {
					// No es el primer paréntesis
					// Estamos en la sublista = <puntero_sublista_actual>
					// Estamos añadiendo un enlace a la nueva sublista
					// <parentesis_abiertos-1+num_sublistas>
					atomos[puntero_sublista_actual][getNumeroAtomosDeSublista(puntero_sublista_actual)] = "_"
							+ Integer.toString(parentesis_abiertos - 1
									+ this.num_sublistas);
				}
				// Buscar sublista libre
				puntero_sublista_actual = getNumeroSublistas();
				if (puntero_sublista_actual >= MAX_SUBLISTAS) {
					// Error: sobrepasa el tope de sublistas
					descError = "Sobrepasado el número máximo de sublistas "
							+ MAX_SUBLISTAS;
					return true;
				}
			} else if (str.equals(")")) {
				// -----------------
				// CERRAR PARÉNTESIS
				// -----------------
				if (parentesis_abiertos == 0) {
					// Paréntesis que no cierra a ninguno
					descError = "Paréntesis que no cierra a ninguno";
					return true;
				} else {
					// Se cierra el paréntesis
					parentesis_abiertos--;
					num_sublistas++;
					// subimos a la sublista padre de la actual
					puntero_sublista_actual = getSublistaPadre(puntero_sublista_actual);
				}
			} else if (!str.equals(" ")) {
				// ---------------
				// TRATAR EL ÁTOMO
				// ---------------
				if (parentesis_abiertos == 0) {
					descError = "Primer átomo fuera de los paréntesis";
					return true;
				}
				num_atomos++;
				atomos[puntero_sublista_actual][getNumeroAtomosDeSublista(puntero_sublista_actual)] = str
						.trim();
			}
		}
		// ---------------
		// FIN DE LA LISTA
		// ---------------
		// Se terminó de recorrer los átomos de la lista
		if (parentesis_abiertos != 0) {
			// ERROR: Paréntesis sin cerrar
			descError = "Paréntesis sin cerrar = " + parentesis_abiertos;
			return true;
		}
		this.lista = this.getString();
		return false;
	}

	public boolean equals(IEstructura estructura) {
		Lista l = (Lista) estructura;
		if (this.getString().equals(l.getString()))
			return true;
		return false;
	}

	public void fromCadena(String texto) {
		this.setLista(texto);
	}

	/**
	 * 
	 * @return Devuelve un String con la descripción del Error.
	 */
	public String getDescripcionError() {
		return this.descError;
	}

	/**
	 * 
	 * @return Devuelve un int con el número de átomos dentro de la lista.
	 */
	public int getNumeroAtomos() {
		return this.num_atomos;
	}

	/**
	 * 
	 * @param Identificador
	 *            numérico de la sublista
	 * @return Devuelve el número de átomos de la sublista especificada
	 */
	public int getNumeroAtomosDeSublista(int sublista) {
		if (sublista == MAX_SUBLISTAS)
			return -1;

		int i = 0;
		while (atomos[sublista][i] != null)
			i++;
		return i;
	}

	/**
	 * 
	 * @return Devuelve el número de sublistas dentro de la lista.
	 */
	public int getNumeroSublistas() {
		int ns = 0;

		for (int i = 0; i < MAX_SUBLISTAS; i++) {
			if (atomos[i][0] != null)
				ns++;
			else
				return ns;
		}
		return MAX_SUBLISTAS;
	}

	/**
	 * 
	 * @return Devuelve la profundidad máxima de la lista. El valor estará
	 *         comprendido entre 1 y 5.
	 */
	public int getProfundidad() {
		return profundidad_maxima;
	}

	/**
	 * 
	 * @return Devuelve un String que contiene el objeto Lista.
	 */
	public String getString() {
		return getSublistaString(0);
	}

	/**
	 * 
	 * @param Identificador
	 *            de la sublista
	 * @return Devuelve un String con la sublista especificada.
	 */
	public Lista getSublista(int sublista) {
		Lista l = new Lista();
		l.setLista(this.getSublistaString(sublista));
		return l;
	}

	private int getSublistaPadre(int hijo) {
		for (int i = 0; i < getNumeroSublistas(); i++) {
			if (isAtomoEnSublista(CARACTERESPECIAL + hijo, i))
				return i;
		}
		return -1;
	}

	/**
	 * @param Identificador
	 *            numérico de la sublista
	 * @return Devuelve la sublista en formato String
	 */
	public String getSublistaString(int sublista) {
		String str = "(";
		boolean espacio = false;

		for (int i = 0; i < getNumeroAtomosDeSublista(sublista); i++) {
			if (atomos[sublista][i].startsWith(CARACTERESPECIAL)) {
				str = str
						+ getSublistaString(Integer
								.parseInt(atomos[sublista][i].substring(1)));
				espacio = false;
			} else {
				if (espacio)
					str = str + " ";
				str = str + atomos[sublista][i];
				espacio = true;
			}
		}
		str = str + ")";
		return str;
	}

	private boolean isAtomoEnSublista(String atomo, int sublista) {
		for (int i = 0; i < getNumeroAtomosDeSublista(sublista); i++) {
			if (atomos[sublista][i].equals(atomo))
				return true;
		}
		return false;
	}

	public boolean isError() {
		return this.error;
	}

	/**
	 * Muestra en la consola el contenido del objeto de tipo Lista.
	 */
	public void mostrar() {
		System.out.println("Lista.mostrar():");
		System.out.println(" * lista              : " + lista);
		System.out.println(" * getProfundidad     : " + getProfundidad());
		System.out.println(" * num_atomos         : " + getNumeroAtomos());
		System.out.println(" * num_sublistas      : " + getNumeroSublistas());
		System.out.println(" * error              : " + isError());
		System.out.println(" * descError          : " + getDescripcionError());
		System.out.println(" * getString          : " + getString());
		System.out.println();

		for (int i = 0; i < MAX_SUBLISTAS; i++) {
			System.out.print("      ");
			for (int j = 0; j < MAX_ATOMOSPORSUBLISTA; j++) {
				System.out.print(atomos[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void setLista(String pEntrada) {
		lista = pEntrada.trim();
		atomos = new String[MAX_SUBLISTAS][MAX_ATOMOSPORSUBLISTA];
		error = false;
		descError = null;
		profundidad_maxima = 0;
		num_atomos = 0;
		num_sublistas = 0;

		error = crearDesdeString(lista);
	}

	public String toCadena() {
		return this.getString();
	}
}
