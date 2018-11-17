package david.gomera.invaders.versiones.v16;

public class Test {
	public static void main(String[] args) {
		String s;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++)
		  s = "bicho"+i+".gif";
		long end = System.currentTimeMillis();
		System.out.println("Modo 1 : "+(end-start));
		
		
    StringBuffer sb;
   	start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			sb = new StringBuffer("bicho");
			sb.append(i);
			sb.append("gif");
		  s = sb.toString();
		}
		end = System.currentTimeMillis();
		System.out.println("Modo 2 : "+(end-start));
		

    String[] frames = {"bicho0.gif","bicho1.gif","bicho2.gif"};		
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
		  s = frames[i%frames.length];
		}
		end = System.currentTimeMillis();
		System.out.println("Modo 3 : "+(end-start));
		
	}

}
