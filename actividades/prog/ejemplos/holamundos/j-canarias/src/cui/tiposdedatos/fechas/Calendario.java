package cui.tiposdedatos.fechas;

import java.util.Calendar;

/**
 * Despliega información de calendario correspondiente al mes de una fecha
 * concreta
 * 
 * @author David Vargas Ruiz
 * @version 1.0.2 - 20051104
 */
public class Calendario {
	/**
	 * @param Número de mes
	 * @return El nombre del mes
	 */
	public static String getNombreMes(int m) {
		if (m == Calendar.APRIL)
			return "Abril";
		if (m == Calendar.AUGUST)
			return "Agosto";
		if (m == Calendar.DECEMBER)
			return "Diciembre";
		if (m == Calendar.FEBRUARY)
			return "Febrero";
		if (m == Calendar.JANUARY)
			return "Enero";
		if (m == Calendar.JULY)
			return "Julio";
		if (m == Calendar.JUNE)
			return "Junio";
		if (m == Calendar.MARCH)
			return "Marzo";
		if (m == Calendar.MAY)
			return "Mayo";
		if (m == Calendar.NOVEMBER)
			return "Noviembre";
		if (m == Calendar.OCTOBER)
			return "Ocubre";
		if (m == Calendar.SEPTEMBER)
			return "Septiembre";
		return "";
	}
	
	public String getNombreMes() {
		return getNombreMes(getMes());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendario c = new Calendario();
		c.mostrar();

		System.out.println("\nDia[1][2]=" + c.getDia(1, 2));

		c.mostrarMatriz();

		System.out.println("\nFiesta el día 1 = " + c.isFiesta(1));
		
		c.mostrarVector();
	}

	/**
	 * Atributos
	 */
	private Calendar cal;

	private String[][] dias;
	
	private Dia[] vectorDias;

	/**
	 * Constructores
	 */
	public Calendario() {
		Calendar c = Calendar.getInstance();
		this.inicializar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}

	public Calendario(int anno, int mes, int dia) {
		this.inicializar(anno, mes, dia);
	}

	private void inicializar(int anno, int mes, int dia) {
		cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(anno, mes, dia);
		dias = getMatriz();
		vectorDias = getVectorDias();
	}
	
	
	public String getDia(int numSemana, int numSemanaDia) {
		return dias[numSemana][numSemanaDia];
	}

	public Dia getDiaByXY(int fila, int columna) {
		int i=1;
		while(i<vectorDias.length) {
			if (vectorDias[i].getNumeroSemanaMes()==fila &&
				vectorDias[i].getNumeroSemanaDia()==columna)
				return vectorDias[i];
			i++;
		}
		return null;
	}
	
	public Dia getDiaByNumero(int dia) {
		if (dia>0&&dia<vectorDias.length) return vectorDias[dia];
		return null;
	}

	public int getAnno() {
		return cal.get(Calendar.YEAR);
	}
	
	public int getMes() {
		return cal.get(Calendar.MONTH)+1;
	}
	
	public int getNumeroDiaActualMes() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public int getNumeroDeDiasDelMes() {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Crea una matriz de String con los dias del mes
	 */
	private String[][] getMatriz() {
		String[][] dias = new String[6][7];
		String espacio;
		int semana; // Contador de semanas
		int ds; // Contador días de la semana
		int numdiasmes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		int anno = cal.get(Calendar.YEAR);
		Calendar calDia;
		calDia = Calendar.getInstance();
		calDia.set(anno, mes, 1);
		calDia.setFirstDayOfWeek(Calendar.MONDAY);

		// Cabecera
		semana = 0;
		ds = 0;
		int j = calDia.get(Calendar.DAY_OF_WEEK);
		if (j == Calendar.SUNDAY)
			j = Calendar.SATURDAY + 1;
		j--;

		for (int i = 1; i < j; i++) {
			dias[semana][ds] = "  ";
			ds++;
		}

		if (ds > 6) {
			semana++;
			ds = 0;
		}

		// Recorrer todos los días del mes
		for (int i = 1; i < numdiasmes + 1; i++) {
			if (i < 10)
				espacio = new String(" ");
			else
				espacio = new String("");

			dias[semana][ds++] = new String(espacio + i);

			if (ds > 6) {
				semana++;
				ds = 0;
			}
		}
		while (ds < 7) {
			dias[semana][ds++] = "  ";
		}
		semana++;
		while (semana < 6) {
			for (int i = 0; i < 7; i++)
				dias[semana][i] = "  ";
			semana++;
		}

		return dias;
	}

	/**
	 * @return una matriz con los días del mes
	 */
	private Dia[] getVectorDias() {
		Dia[] vector = new Dia[31];
		int semana=0; // Contador de semanas
		int ds=0;
		Calendar c  = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		
		// Recorrer todos los días del mes
		for (int i=0;i<this.getNumeroDeDiasDelMes();i++) {
			c.set(this.getAnno(), this.getMes(),i+1);
			ds = c.get(Calendar.DAY_OF_WEEK);
			if (ds == Calendar.SUNDAY)
				ds = Calendar.SATURDAY + 1;

			vector[i]= new Dia(this.getAnno(),this.getMes(),i+1);
			vector[i].setNumeroSemanaMes(semana);
			vector[i].setNumeroSemanaDia(ds);
			vector[i].setFiesta(this.isFiesta(i+1));

			if (ds+1 > 6) semana++;
		}
		return vector;
	}

	/**
	 * @param diaMes Número del dia del mes
	 * @return true si esa fecha es fiesta
	 */
	public boolean isFiesta(int dia) {
		Calendar calDia;
		calDia = Calendar.getInstance();
		calDia.set(this.getAnno(), this.getMes(), dia);
		calDia.setFirstDayOfWeek(Calendar.MONDAY);

		int j = calDia.get(Calendar.DAY_OF_WEEK);
		if (j == Calendar.SUNDAY || j == Calendar.SATURDAY)
			return true;
		if (getMes() == 1 && (dia == 1 || dia == 6))
			return true;
		if (getMes() == 11 && dia == 1)
			return true;
		if (getMes() == 12 && (dia == 6 || dia == 8 || dia == 24 || dia == 31))
			return true;

		return false;
	}

	/**
	 * Muestra en la consola la información de calendario del mes asociado a la
	 * fecha
	 */
	public void mostrar() {
		String espacio;
		// int diasemana = cal.get(Calendar.DAY_OF_WEEK);
		int diahoy = cal.get(Calendar.DAY_OF_MONTH);
		int numdiasmes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		int anno = cal.get(Calendar.YEAR);
		Calendar calDia;
		calDia = Calendar.getInstance();
		calDia.setFirstDayOfWeek(Calendar.MONDAY);
		calDia.set(anno, mes, 1);

		System.out.println("   " + getNombreMes(mes) + " - " + anno);
		System.out.println("   L   M   X   J   V   S   D");
		// Cabecera
		int j = calDia.get(Calendar.DAY_OF_WEEK);
		if (j == Calendar.SUNDAY)
			j = Calendar.SATURDAY + 1;

		for (int i = 1; i < j - 1; i++) {
			System.out.print("    ");
		}

		// Imprimir para cada día del mes
		for (int i = 1; i < numdiasmes + 1; i++) {
			if (i < 10)
				espacio = new String(" ");
			else
				espacio = new String("");

			if (i == diahoy) {
				System.out.print(" [" + espacio + i + "]");
			} else if (i - 1 == diahoy)
				System.out.print(" " + espacio + i);
			else
				System.out.print("  " + espacio + i);

			calDia.set(anno, mes, i);
			if (calDia.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				System.out.println();
		}
		System.out.println();
	}

	public void mostrarMatriz() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(" " + dias[i][j]);
			}
			System.out.println();
		}
	}

	public void mostrarVector() {
		for (int i=0;i<vectorDias.length-1;i++) {
			System.out.print(i);//TODO
			if (vectorDias[i]!=null) vectorDias[i].mostrar();
		}
	}

}
