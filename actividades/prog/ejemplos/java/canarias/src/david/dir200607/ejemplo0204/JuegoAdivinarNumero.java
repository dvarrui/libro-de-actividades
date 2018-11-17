package david.dir200607.ejemplo0204;

import java.util.Random;

public class JuegoAdivinarNumero {
	public static final int NUMERO_MINIMO=1;
	public static final int NUMERO_MAXIMO=100;
	private static final int VACIO=-1;

	private Random generadorNumerosAleatorios;
	private int numeroSecreto;
	private int numeroFallos;
	private int listadoDeFallos[];
	private boolean partidaTerminada;

	
	public JuegoAdivinarNumero() {
		generadorNumerosAleatorios = new Random();
		listadoDeFallos = new int[NUMERO_MAXIMO-NUMERO_MINIMO];
		this.empezarPartida();
	}
	
	public void empezarPartida() {
		//Inicializar las variables al comienzo de la partida
		numeroSecreto=NUMERO_MINIMO+generadorNumerosAleatorios.nextInt(NUMERO_MAXIMO+1);
		if (numeroSecreto==VACIO) numeroSecreto=NUMERO_MAXIMO;
		
		numeroFallos=0;
		for(int i=0;i<listadoDeFallos.length;i++) {
			listadoDeFallos[i]=VACIO;
		}
		partidaTerminada=false;
	}

	public boolean esNumeroSecretoIgualA(int numero) {
		if (numero==VACIO) return false;
		if (numero==numeroSecreto) {partidaTerminada=true; return true;}
	
		if (!esUnFalloRepetidoElNumero(numero)) {
			//Registrar el fallo
			listadoDeFallos[numeroFallos]=numero;
			numeroFallos++;
		}
		return false;
	}
	
	private boolean esUnFalloRepetidoElNumero(int numero) {
		for (int i=0;i<numeroFallos;i++) {
			if (listadoDeFallos[i]==numero) return true;
		}
		return false;
	}
	
	public String getFallosRegistrados() {
		String salida="";
		for(int i=0;i<numeroFallos;i++) {
			salida = salida+","+listadoDeFallos[i];
		}
		return salida;
	}
	
	public int getNumeroFallos() {return this.numeroFallos;}
	
	public int getNumeroSecreto() {
		if (partidaTerminada) return this.numeroSecreto;
		return VACIO;
	}
}
