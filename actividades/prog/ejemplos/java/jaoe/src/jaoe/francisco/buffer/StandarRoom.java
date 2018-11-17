package jaoe.francisco.buffer;


import jaoe.comun.invaders.*; //Añadido por DVR 20060507


import java.awt.Canvas;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Font;
    import java.awt.Graphics2D;
    import java.awt.Rectangle;
    import java.awt.TexturePaint;
    
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    
    import java.awt.image.BufferStrategy;
    import java.awt.image.BufferedImage;
    
    import java.util.ArrayList;
    import java.awt.BorderLayout;
    
    
    import javax.swing.JFrame;
import javax.swing.JPanel;
    /*He cambiado el nombre a la clase, para poder usar una clase padre
    a modo de clase Object en Java. Si te fijas, ocure algo parecido 
    con la clase Actor y sus hijas. A la hora de actualizar todos los 
    Actors de la Room, los convierte a Actor y ejecuta indistintamente
    sus m�todos de actualizaci�n. Esta clase podr�a ir vacia, y que to
    das las Rooms heredaran de ella. No he dejado los m�todos vacios para
    ir probando con esta misma clase*/
    
    public class StandarRoom extends Canvas implements Room, KeyListener{
      
      private BufferStrategy strategy;
      private long usedTime;
      private SpriteCache spriteCache;
      private ArrayList actors; 
      //public Player player;
			private JPanel room;
      private BufferedImage ocean;
      private int t;
                  
      public StandarRoom() {
        /*Ahora el constructor no crea el BufferStrategy, lo hace la clase Juego
        ya que saltaban excepciones si la creaba aqui y la usaba en otro sitio*/
      	        
        room = new JPanel();
               
        room.setLayout(new BorderLayout());
        
        room.add(this);
      	
        spriteCache = new SpriteCache();
                    
        setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
                   
        addKeyListener(this);  
        
        initWorld();
        
        }
        
              	     		
      
      public void initWorld() {
        actors = new ArrayList();
        
        usedTime=1000;

       	t = 0;
        for (int i = 0; i < 10; i++){
          Monster m = new Monster(this);
          m.setX( (int)(Math.random()*Room.WIDTH) );
          m.setY( i*20 );
          m.setVx( (int)(Math.random()*20-10) );
                    
          actors.add(m);
        }
        
        
      }
      
      
      public void addActor(Actor a) {
      	actors.add(a);
      } 
      
      
         
      
      public void updateWorld() {
        int i = 0;
        while (i < actors.size()) {
          Actor m = (Actor)actors.get(i);
          if (m.isMarkedForRemoval()) {
           actors.remove(i);
         } else {
           m.step();/*Cambi� el nombre del m�todo act() por step(), simplemente porque
           en varios foros se usaba ese t�rmino*/
           i++;
         }
       }
       Juego.player.step();
     }
     
        
     
     
     public void checkCollisions() {
       Rectangle playerBounds = Juego.player.getBounds();
       for (int i = 0; i < actors.size(); i++) {
         Actor a1 = (Actor)actors.get(i);
         Rectangle r1 = a1.getBounds();
         if (r1.intersects(playerBounds)) {
           Juego.player.collision(a1);
           a1.collision(Juego.player);
         }
         for (int j = i+1; j < actors.size(); j++) {
           Actor a2 = (Actor)actors.get(j);
           Rectangle r2 = a2.getBounds();
           if (r1.intersects(r2)) {
             a1.collision(a2);
             a2.collision(a1);
           }
         }
       }
     }
     
     
     public void paintShields(Graphics2D g) {
       g.setPaint(Color.red);
       g.fillRect(280,Room.PLAY_HEIGHT,Player.MAX_SHIELDS,30);
       g.setPaint(Color.blue);
       g.fillRect(280+Player.MAX_SHIELDS-Juego.player.getShields(),Room.PLAY_HEIGHT,Juego.player.getShields(),30);
       g.setFont(new Font("Arial",Font.BOLD,20));
       g.setPaint(Color.green);
       g.drawString("Shields",170,Room.PLAY_HEIGHT+20);
         
     }
       
     public void paintScore(Graphics2D g) {
       g.setFont(new Font("Arial",Font.BOLD,20));
       g.setPaint(Color.green);
       g.drawString("Score:",20,Room.PLAY_HEIGHT + 20);
       g.setPaint(Color.red);
       g.drawString(Juego.player.getScore()+"",100,Room.PLAY_HEIGHT  + 20);
     }
     
     public void paintAmmo(Graphics2D g) {
       int xBase = 280+Player.MAX_SHIELDS+10;
       for (int i = 0; i < Juego.player.getClusterBombs();i++) {
         BufferedImage bomb = spriteCache.getSprite("bombUL.gif");
         g.drawImage( bomb ,xBase+i*bomb.getWidth(),Room.PLAY_HEIGHT,this);
       }
     }
     
     public void paintfps(Graphics2D g) {
       g.setFont( new Font("Arial",Font.BOLD,12));
       g.setColor(Color.white);
       if (usedTime > 0)
         g.drawString(String.valueOf(1000/usedTime)+" fps",Room.WIDTH-50,Room.PLAY_HEIGHT);
       else
         g.drawString("--- fps",Room.WIDTH-50,Room.PLAY_HEIGHT);
     }
     
     
     public void paintStatus(Graphics2D g) {
       paintScore(g);
       paintShields(g);
       paintAmmo(g);
       paintfps(g);  
     }
     
     public void paintWorld(BufferStrategy a) {
       /*Este m�todo recibe un BufferStrategy como par�metro ahora. El motivo es que
       antes simplemente cog�a el BufferStrategy (strategy me parece que se llamaba
       la variable) y lo utilizaba. Como esto provocaba excepciones (a�n no se porqu�)
       ahora recibe su propio BufferStrategy, creado desde otro sitio*/
     	 Graphics2D g = (Graphics2D)a.getDrawGraphics();

       ocean = spriteCache.getSprite("oceano.gif");
       
       g.setPaint(new TexturePaint(ocean, new Rectangle(0,t,ocean.getWidth(),ocean.getHeight())));
       
       g.fillRect(0,0,getWidth(),getHeight());

       for (int i = 0; i < actors.size(); i++) {
         Actor m = (Actor)actors.get(i);
         m.paint(g);
       }
       
       Juego.player.paint(g);
            
       paintStatus(g);
       
       a.show();
       
       
       
     }
     
     public void paintGameOver(BufferStrategy a) {
       Graphics2D g = (Graphics2D)a.getDrawGraphics();
       g.setColor(Color.white);
       g.setFont(new Font("Arial",Font.BOLD,20));
       g.drawString("Perdiste",Room.WIDTH/2-50,Room.HEIGHT/2);
       a.show();
     }
     
     public SpriteCache getSpriteCache() {
       return spriteCache;
     }
     
     public void keyPressed(KeyEvent e) {
       Juego.player.keyPressed(e);
     }
     
     public void keyReleased(KeyEvent e) {
       Juego.player.keyReleased(e);
     }
     public void keyTyped(KeyEvent e) {}
			
     public JPanel getRoom(){ return room;}
     
          
     public void game(BufferStrategy a) {
 			/*Recibe el BufferStrategy como par�metro para poder enviarselo al
 			m�todo paintWorld*/
      
         t++;

         long startTime = System.currentTimeMillis();
         updateWorld();
         checkCollisions();
         paintWorld(a);
         usedTime = System.currentTimeMillis()-startTime;
         try { 
            Thread.sleep(SPEED);
         } catch (InterruptedException e) {System.out.println();}


      
      

     }
    
     		
          
    }
