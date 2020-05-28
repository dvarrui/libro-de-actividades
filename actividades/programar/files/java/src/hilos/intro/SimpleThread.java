package hilos.intro;

public class SimpleThread extends Thread {
	int retardo=100;
	
    // constructor
    public SimpleThread (String str, int retardo) {
        super(str);
        this.retardo=retardo;
    }
    // redefinición del método run()
    public void run() {
        for(int i=0;i<10;i++)  {
           System.out.println("Este es el thread : " + getName());
           try {
        	   Thread.sleep(retardo);
           } catch(Exception e) {
        	   System.err.println(" [ERROR] "+e);
           }
        }
    }
    
    public static void main(String args[]) {
    	SimpleThread s1 = new SimpleThread("Hilo 01",10);
    	SimpleThread s2 = new SimpleThread("Hilo 02",20);
    	
    	s1.start();
    	s2.start();
    	
    }
}
