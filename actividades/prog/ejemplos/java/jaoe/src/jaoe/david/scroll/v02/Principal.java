package jaoe.david.scroll.v02;

import java.awt.Canvas;
import java.awt.Color;
//import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
//import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends Canvas implements Stage, KeyListener {
	public static final long serialVersionUID = 1;

	public static void main(String[] args) {
		Principal inv = new Principal();
		inv.game();
	}

	private BufferedImage background, backgroundTile;

	private int fondoY, fondoX;

	private boolean gameEnded = false;

	private SpriteCache spriteCache;

	private BufferStrategy strategy;

	//private long usedTime;

	public Principal() {
		spriteCache = new SpriteCache();

		JFrame ventana = new JFrame("Scroll v.02");
		JPanel panel = (JPanel) ventana.getContentPane();
		setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		panel.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ventana.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		addKeyListener(this);

		setIgnoreRepaint(true);
	}

	public void game() {
		//usedTime = 1000;
		initWorld();
		while (isVisible() && !gameEnded) {
			long startTime = System.currentTimeMillis();
			//fondoY--;
			//fondoX--;
			//if (fondoY < 0)
			//	fondoY = backgroundTile.getHeight();

			//if (fondoX<0)
			//	fondoX = backgroundTile.getWidth();
			
			paintWorld();
			//usedTime = System.currentTimeMillis() - startTime;
			do {
				Thread.yield();
			} while (System.currentTimeMillis() - startTime < 17);
		}
		//paintGameOver();
	}

	public void gameOver() {
		gameEnded = true;
	}

	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	public void initWorld() {
		backgroundTile = spriteCache.getSprite("imagen01.jpg");
		background = spriteCache.createCompatible(
				Stage.WIDTH + backgroundTile.getWidth(), 
				Stage.HEIGHT + backgroundTile.getHeight(), Transparency.OPAQUE);
		Graphics2D g = (Graphics2D) background.getGraphics();
		g.setPaint(new TexturePaint(backgroundTile, new Rectangle(0, 0,
				backgroundTile.getWidth(), backgroundTile.getHeight())));
		g.fillRect(0, 0, background.getWidth(), background.getHeight());
		
		//fondoY = backgroundTile.getHeight();
		//fondoX = backgroundTile.getWidth();
		fondoY=0;
		fondoX=0;
	}

	public void keyPressed(KeyEvent e) {
		// player.keyPressed(e);
		//37,38,39,40 (I,A,D,B)
		
		if (e.getKeyCode()==38) {
			if (fondoY > 0)
				fondoY=fondoY-10;
				fondoY = ((fondoY<0)?0:fondoY);
		} else if(e.getKeyCode()==40) {
			if (fondoY < (backgroundTile.getHeight()-this.getHeight()))
				fondoY=fondoY+10;
		} else if(e.getKeyCode()==37) {
			if (fondoX > 0)
				fondoX=fondoX-10;
				//fondoX = backgroundTile.getWidth();
		} else if(e.getKeyCode()==39) {
			if (fondoX < (backgroundTile.getWidth()-this.getWidth()))
				fondoX = fondoX+10;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// player.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void paintfps(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.white);
		
		g.drawString(" X="+fondoX+" Y="+fondoY
				+" imgX="+backgroundTile.getWidth()
				+" imgY="+backgroundTile.getHeight()
				+" frmX="+this.getWidth()
				+" frmY="+this.getHeight(), Stage.WIDTH - 500, Stage.PLAY_HEIGHT);
	}


	public void paintStatus(Graphics2D g) {
		paintfps(g);
	}

	public void paintWorld() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.drawImage(background, 0, 0, Stage.WIDTH, Stage.HEIGHT, fondoX,
				fondoY, fondoX+Stage.WIDTH, fondoY + Stage.HEIGHT, this);

		paintStatus(g);
		strategy.show();
	}
}
