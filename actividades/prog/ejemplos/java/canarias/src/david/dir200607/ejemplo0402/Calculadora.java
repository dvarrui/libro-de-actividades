package david.dir200607.ejemplo0402;

public class Calculadora {
	public static final char DIVISION = '/';

	private static final int INICIAL = 0;

	public static final char MULTIPLICACION = '*';

	private static final int NUMERO1 = 1;

	private static final int NUMERO1_Y_OPERADOR = 2;

	public static final char RESTA = '-';

	public static final char SUMA = '+';

	public static void main(String args[]) {
		Calculadora cal = new Calculadora();
		cal.setNumero(1);
		System.out.println(cal.getEstado());
		cal.setOperador(Calculadora.SUMA);
		System.out.println(cal.getEstado());
		cal.setNumero(2);
		System.out.println(cal.getEstado());
		System.out.println(cal.getAcumulador());
	}

	private double acumulador;

	private int estado;

	private double memoria;

	private char operador;

	Calculadora() {
		this.acumulador = 0;
		this.operador = '#';
		this.memoria = 0;
		this.estado = Calculadora.INICIAL;
	}

	public void ejecutarAC() {
		this.acumulador = 0;
		this.estado = Calculadora.INICIAL;
	}

	public void ejecutarMA(String memoria) {
		this.ejecutarMA(Double.parseDouble(memoria));	
	}
	
	public void ejecutarMA(double memoria) {
		this.memoria = this.memoria + memoria;
	}

	public void ejecutarMC() {
		this.memoria = 0;
	}

	public double ejecutarMR() {
		return memoria;
	}

	public double getAcumulador() {
		return this.acumulador;
	}

	public String getEstado() {
		String salida = "Error";

		if (this.estado == Calculadora.INICIAL) {
			salida = "Acumulador a cero";
		} else if (this.estado == Calculadora.NUMERO1) {
			salida = "Valor actual " + this.acumulador;
		} else if (this.estado == Calculadora.NUMERO1_Y_OPERADOR) {
			salida = "Operación pendiente " + this.acumulador + " "
					+ this.operador;
		}
		return salida;
	}

	public void setNumero(double numero) {
		if (this.estado == Calculadora.INICIAL) {
			this.acumulador = numero;
			this.estado = Calculadora.NUMERO1;
		} else if (this.estado == Calculadora.NUMERO1) {
			this.acumulador = numero;
		} else if (this.estado == Calculadora.NUMERO1_Y_OPERADOR) {
			switch (this.operador) {
			case Calculadora.SUMA:
				this.acumulador = this.acumulador + numero;
				break;
			case Calculadora.RESTA:
				this.acumulador = this.acumulador - numero;
				break;
			case Calculadora.MULTIPLICACION:
				this.acumulador = this.acumulador * numero;
				break;
			case Calculadora.DIVISION:
				this.acumulador = this.acumulador / numero;
			}
			this.estado = Calculadora.NUMERO1;
		}
	}

	public void setNumero(String numero) {
		this.setNumero(Double.parseDouble(numero));
	}

	public void setOperador(char operador) {
		if (this.estado == Calculadora.NUMERO1) {
			// Sólo si antes se ha instroducido el primer operando
			// tendremos en cuenta el operador
			if ("+-*/".indexOf(operador) >= 0) {
				// Si el operador es correcto realizamos el cambio de estado
				this.operador = operador;
				this.estado = Calculadora.NUMERO1_Y_OPERADOR;
			}
		}
	}

	public void setOperador(String operador) {
		if (operador.equals(Character.toString(Calculadora.SUMA)))
			this.setOperador(Calculadora.SUMA);
		if (operador.equals(Character.toString(Calculadora.RESTA)))
			this.setOperador(Calculadora.RESTA);
		if (operador.equals(Character.toString(Calculadora.MULTIPLICACION)))
			this.setOperador(Calculadora.MULTIPLICACION);
		if (operador.equals(Character.toString(Calculadora.DIVISION)))
			this.setOperador(Calculadora.DIVISION);
	}
}
