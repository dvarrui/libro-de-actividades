package hilos.intro;

public class SimpleRunnable implements Runnable {
	int retardo = 100;
    // se crea un nombre
    String nameThread;
    // constructor
    public SimpleRunnable (String str, int retardo) {
        nameThread = str;
        this.retardo=retardo;
    }

    // definición del método run()
    public void run() {
        for(int i=0;i<10;i++) {
           System.out.println("Este es el thread: " + nameThread);
           try {
        	   Thread.sleep(retardo);
           } catch(Exception e) {
        	   System.err.println(" [ERROR] "+e);
           }
        }
    }

    public static void main(String[] args) {
		SimpleRunnable s1 = new SimpleRunnable("Hilo 01",10);
    	SimpleRunnable s2 = new SimpleRunnable("Hilo 02",20);
    	
    	Thread t1 = new Thread(s1);
    	Thread t2 = new Thread(s2);
    	
    	t1.start();
    	t2.start();
	}
}

