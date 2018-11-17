package david.gomera.scroll.v01;

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

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends Canvas implements Stage, KeyListener {
	public static final long serialVersionUID=1;
	
	private BufferStrategy strategy;
	private long usedTime;
	
	private SpriteCache spriteCache;
	private BufferedImage fondo;
	private int t;
	
	private boolean gameEnded=false;
	
	public Principal() {
		spriteCache = new SpriteCache();
	
		JFrame ventana = new JFrame("Scroll v.01");
		JPanel panel = (JPanel)ventana.getContentPane();
		setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
		panel.setPreferredSize(new Dimension(Stage.WIDTH,Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ventana.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		addKeyListener(this);
	}
	
	public void gameOver() {
		gameEnded = true;
	}
	
	public void initWorld() {
      //actors = new ArrayList();
	}
	

		
	public void paintfps(Graphics2D g) {
		g.setFont( new Font("Arial",Font.BOLD,12));
		g.setColor(Color.white);
		if (usedTime > 0)
		  g.drawString(String.valueOf(1000/usedTime)+" fps",Stage.WIDTH-50,Stage.PLAY_HEIGHT);
  	else
	  	g.drawString("--- fps",Stage.WIDTH-50,Stage.PLAY_HEIGHT);
	}
	
	
	
	public void paintWorld() {
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		fondo = spriteCache.getSprite("imagen01.jpg");
		g.setPaint(new TexturePaint(fondo, new Rectangle(0,t,fondo.getWidth(),fondo.getHeight())));
		g.fillRect(0,0,getWidth(),getHeight());
		/*for (int i = 0; i < actors.size(); i++) {
			Actor m = (Actor)actors.get(i);
			m.paint(g);
		}
		player.paint(g);*/

	  	
	  //paintStatus(g);
		strategy.show();
	}
	
	public void paintGameOver() {
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("GAME OVER",Stage.WIDTH/2-50,Stage.HEIGHT/2);
		strategy.show();
	}
	
	public SpriteCache getSpriteCache() {
		return spriteCache;
	}
	
	public void keyPressed(KeyEvent e) {
		//player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		//player.keyReleased(e);
	}
	public void keyTyped(KeyEvent e) {}
	
	public void game() {
		usedTime=1000;
		t = 0;
		initWorld();
		while (isVisible() && !gameEnded) {
			t++;
			long startTime = System.currentTimeMillis();
			//updateWorld();
			//checkCollisions();
			paintWorld();
			usedTime = System.currentTimeMillis()-startTime;
			try { 
				 Thread.sleep(SPEED);
			} catch (InterruptedException e) {}
		}
		paintGameOver();
	}
	
	public static void main(String[] args) {
		Principal inv = new Principal();
		inv.game();
	}
}
