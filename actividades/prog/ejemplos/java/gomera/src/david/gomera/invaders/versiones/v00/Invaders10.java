package david.gomera.invaders.versiones.v00;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Invaders10 extends Canvas {
	static final long serialVersionUID=1;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int SPEED = 1;
	
	public HashMap sprites;
	public int posX,posY,vX;
	public BufferedImage buffer;
	
	public Invaders10() {
		sprites = new HashMap();
		posX = WIDTH/2;
		posY = HEIGHT/2;
		vX = 2;
		buffer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
	
		JFrame ventana = new JFrame("Invaders");
		
		JPanel panel = (JPanel)ventana.getContentPane();
		setBounds(0,0,WIDTH,HEIGHT);
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0,0,WIDTH,HEIGHT);
		ventana.setVisible(true);
		ventana.createBufferStrategy(2);
		ventana.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ventana.setIgnoreRepaint(true);
		ventana.setResizable(false);
	}
	
	public BufferedImage loadImage(String nombre) {
		URL url=null;
		try {
			url = getClass().getClassLoader().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String nombre) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if (img == null) {
			img = loadImage("david/gomera/invaders/images/"+nombre);
			sprites.put(nombre,img);
		}
		return img;
	}
	
	public void paintWorld() {
		Graphics g = buffer.getGraphics();
		g.setColor(getBackground());
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(getSprite("bicho/bicho.gif"), posX, posY,this);
	}
	

	public void paint(Graphics g) {
		g.drawImage(buffer,0,0,this);
	}
	
	public void updateWorld() {
		posX += vX;
		if (posX < 0 || posX > WIDTH) vX = -vX;
	}
	
	public void game() {
		while (isVisible()) {
			updateWorld();
			paintWorld();
			paint(getGraphics());
			try { 
				 Thread.sleep(SPEED);
			} catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		Invaders10 inv = new Invaders10();
		inv.game();
	}
}
