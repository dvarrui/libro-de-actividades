package jaoe.francisco.buffer;

import jaoe.comun.invaders.Player; //Añadido por DVR 20060507

import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    import javax.swing.*;
    import java.awt.Dimension;
		import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
		import java.awt.image.BufferStrategy;
		import java.awt.Graphics2D;
		import java.awt.TexturePaint;
		import java.awt.Rectangle;

    import java.awt.Canvas;
    import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
    



public class Juego extends JFrame implements KeyListener {
		
	//AQUI VAN LAS VARIABLES
	//#################################################		
			public BufferStrategy painter;//Ser� el encargado de dibujar
			public StandarRoom roomActive;//Ser� la Room activa en ese momento
			public static boolean gameEnded;/*Ahora es esta clase quien decide
			cuando acabar� el juego. Como puede ser modificado desde cualquier
			Room, tanto la variable como el m�todo que modifica la variable 
			son est�ticos*/
			public static Player player; /*Ahora el problema que tenemos es que
			antes, el player solo pertenec�a a una Room, por lo que se creaba
			y se modificaba en ella. Lo �nico que se me ha ocurrido para que
			el player no dependa de la Room en la que est� es hacerlo est�tico
			en la clase Juego. S� que no es la mejor soluci�n, espero que se
			nos ocurra otra*/
			
	//#################################################
	
	
			
			//AQUI VAN LOS CONSTRUCTORES
	//#################################################
			
	
      public Juego(StandarRoom newRoom){
      	
      	/*El juego se iniciar� con la Room inicial*/
      	
      	super("Proyecto de Juego");
      	
      	roomActive = newRoom;
      	
      	setContentPane(roomActive.getRoom());
      	
      	setBounds(0,0,Room.WIDTH,Room.HEIGHT);
        
      	setVisible(true);
        
      	addWindowListener( new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
        
        setResizable(false);
        
        getPainter();//Ver comentario dentro del m�todo
        
      	requestFocus();
      	
      	gameEnded = false;
        
        addKeyListener(this);
        
        player = new Player(roomActive);/*Aunque el player no dependa de la Room ahora,
        a�n lo tiene como par�metro en el constructor. Todavia no he modificado esa cla
        se, as� que lo que hice fue pasarlo como par�metro, y cambiar la room del juga
        dor a la vez que la room de la clase Juego. Un poco chapucero*/
        
        player.setX(Room.WIDTH/2);
        
        player.setY(Room.PLAY_HEIGHT - 2*player.getHeight());

        
             
      }
      
    //###########################################################  
      
     
      //Resto de m�todos
    //##########################################################  
      public void keyPressed(KeyEvent e) {
       	/*Como quien va a decidir que ocurre cuando se acciona un evento, es, al fin
      y al cabo la room donde se encuentre el jugador, lo �nico que hace es llamar
      al evento de dicha room, que se encargar� de ejecutarlo. Por ejemplo, cuando
      estamos en un escenario, al pulsar la tecla hacia arriba, nos desplazamos.
      Pero en un men�, cambia la opci�n*/

      	roomActive.keyPressed(e);
     }
     
     
     public void keyReleased(KeyEvent e) {
      
     		/*Como quien va a decidir que ocurre cuando se acciona un evento, es, al fin
      y al cabo la room donde se encuentre el jugador, lo �nico que hace es llamar
      al evento de dicha room, que se encargar� de ejecutarlo. Por ejemplo, cuando
      estamos en un escenario, al pulsar la tecla hacia arriba, nos desplazamos.
      Pero en un men�, cambia la opci�n*/
     	
      roomActive.keyReleased(e);
     
     }
     
     public void keyTyped(KeyEvent e) {}
		
     
     public boolean isEnded() { return gameEnded;}  	 
     
     
       
                   
     public void getPainter() {
     	 
     		/*Este m�todo parece un poco raro, pero es la �nica forma que no da
     excepciones. El problema viene del BufferStrategy. Se debe crear un
     BufferStrategy espec�fico para cada componente, por lo que crear un
     BufferStrategy en esta clase y enviarla como par�metro para que pin
     te la Room es imposible. Tambi�n prob� crear el BufferStrategy en 
     la propia Room, y enviarlo como par�metro por un m�todo, guard�ndo
     lo en una variable de esta clase. Tampoco funcion�. As� que opt� 
     por la forma m�s bruta: crear el BufferStrategy en la Room, si, pe
     ro desde aqui. Luego lo encapsul� en un m�todo para que sea trans
     parente a la hora de cambiar las Rooms. Ese es el motivo por el que
     paso como par�metro a la Room su propio BufferStrategy. Seguro que 
     hay formas m�s finas de hacerlo.*/
     	 
     	 roomActive.createBufferStrategy(2);
        
       painter = roomActive.getBufferStrategy();
      

     
     }
     
     
     
     public static void gameOver(){ gameEnded = true;}
     
     
     
     
     public void changeRoom(StandarRoom newRoom){
     		/*Este m�todo cambia la Room actual por la enviada como
     	par�metro*/
     	
     		roomActive = newRoom;
      	
      	setContentPane(roomActive.getRoom());
      	      	                      
        getPainter();//Ver comentario dentro del m�t     	
     
     }
     
     
     public void game(BufferStrategy a) {
     		
     			/* Este m�todo lo �nico que hace es llamar al m�todo game() de
     		la Room activa en ese momento. Ahora dicho m�todo recibe un 
     		BufferStrategy como par�metro. Como el m�todo game() de la cla
     		se Juego ser� ejecutado desde un main, tambi�n hay que enviarle
     		el par�metro para que se lo entregue al game() de la Room*/
     	
     	while(!gameEnded){
     		

     		roomActive.game(a);
     		     	
     		
     	}
      
     	roomActive.paintGameOver(a);
     
     
     }
     
     public static void main(String [] args){
     	
     	Juego juego = new Juego(new StandarRoom());
     	
     	juego.game(juego.painter);
     	
     	
     	
     
     }
    
     	
  }


	