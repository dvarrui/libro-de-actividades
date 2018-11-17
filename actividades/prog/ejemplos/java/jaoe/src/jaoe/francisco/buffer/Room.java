package jaoe.francisco.buffer;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.image.BufferStrategy;

import jaoe.comun.invaders.*;

public interface Room extends ImageObserver {
			public static final int WIDTH=640;
      public static final int HEIGHT=480;
      public static final int PLAY_HEIGHT = 400; 
      public static final int SPEED=10;

	public void initWorld();//Inicia el escenario
	public void updateWorld();//Actualiza el escenario
	public void addActor(Actor a);//A�ade un elemento al escenario
	//public Player getPlayer();//Devuelve el Jugador
	public SpriteCache getSpriteCache();//Devuelve un sprite
	//public void setPlayer(Player player);//Asigna un jugador al Room
	public void keyPressed(KeyEvent e);//Detecta una tecla pulsada
	public void keyReleased(KeyEvent e);//Detecta una tecla soltada
	public void keyTyped(KeyEvent e);//Detecta una tecla concreta
	public void game(BufferStrategy a);//Ejecuta la Room
	public JPanel getRoom();

}