package david.tenerife.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Esta clase sirve para generar números aleatorios, fechas aleatorias textos o
 * Strings aleatorios, etc. Sirve para generar valores aleatorios de cualquier
 * tipo.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051128
 * 
 */
public class RandomMultiple {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomMultiple r = new RandomMultiple();
		for (int i = 0; i < 10; i++) {
			System.out.println(r.nextInt() + "|" + r.nextDate() + "|"
					+ r.nextString());
		}

	}

	Random azar;

	Calendar cal;

	public RandomMultiple() {
		azar = new Random();
		cal = Calendar.getInstance();
	}

	public boolean nextBoolean() {
		boolean b = true;
		if (this.nextInt(1) == 1)
			b = false;
		return b;
	}

	public Date nextDate() {
		int ano;
		int mes;
		int dia;

		ano = cal.get(Calendar.YEAR);
		mes = cal.get(Calendar.MONTH);
		dia = cal.get(Calendar.DAY_OF_MONTH);

		ano = ano + azar.nextInt(3) - azar.nextInt(3);
		mes = mes + azar.nextInt(3) - azar.nextInt(3);
		if (mes < 1)
			mes = 1;
		if (mes > 12)
			mes = 12;

		dia = dia + azar.nextInt(3) - azar.nextInt(3);
		if (dia < 1)
			dia = 1;
		if (dia > 28)
			dia = 28;

		cal.set(ano, mes, dia);
		return cal.getTime();
	}

	public int nextInt() {
		return azar.nextInt();
	}

	public int nextInt(int valor) {
		return azar.nextInt(valor);
	}

	public String nextString() {
		int longitud = azar.nextInt(20);
		String abc = "abcdefghijklmnñopqrstuvwxyz ";
		char c[] = abc.toCharArray();

		StringBuilder sb = new StringBuilder(longitud);
		for (int i = 0; i < longitud; i++) {
			sb.append(c[azar.nextInt(abc.length())]);
		}
		
		if (sb.length()==0) sb.append(".");
		
		return sb.toString();
	}

}
