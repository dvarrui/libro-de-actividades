package david.gomera.juego2d;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Escenario {
	public static final String LIBRE=".";
	public static final String PARED="O";
	public static final String PELIGRO="$";
	public static final String FINAL="*";
	public static final String RATON="@";

	private String escena[][];
	private String fichero;
	private String version;
	private int ratonX;
	private int ratonY;
	
	public Escenario(String pFichero) {
		fichero = pFichero;
		ratonX = ratonY = 0;
		load();
	}
	
	public void load() {
		BufferedReader br;
		int x,y;
		StringTokenizer st;
		
		try {
			br = new BufferedReader(new FileReader(fichero));
			version = br.readLine().trim();
			x = Integer.parseInt(br.readLine());
			y = Integer.parseInt(br.readLine());
			escena = new String[y][x];
			for(int i=0;i<y;i++) {
				String fila = br.readLine().trim();
				int j=0;
				st = new StringTokenizer(fila," ");
				while(st.hasMoreTokens()) {
					escena[i][j]=st.nextToken();
					if (escena[i][j].equals(Escenario.RATON)) {
						ratonY=i; ratonX=j;
					}
					j++;
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR load(): " + e);
		}
	}
	
	public void save(String salida) {
		//salida = new PrintWriter(new BufferedWriter(new FileWriter(
		//		nombreDestino)));
		//fin = 0;
		
	}
	
	public void mostrar() {
		System.out.println("Fichero :"+fichero);
		System.out.println("Versión :"+version);
		System.out.println("Alto    :"+escena.length);
		System.out.println("Ancho   :"+escena[0].length);
		System.out.println("Ratón   :"+ratonY+","+ratonX+"\n");
		for(int i=0;i<escena.length;i++) {
			for(int j=0;j<escena[i].length;j++)
				System.out.print(escena[i][j]);
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Escenario e = new Escenario("datos/laberinto/escena01.txt");
		e.mostrar();
	}
}
