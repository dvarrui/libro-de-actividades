package david.gomera.invaders.versiones.v24;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Esta clase es la encargada de leer los Sprites de sus archivos de definición
 * y mantenerlos en una caché.
 * 
 */
public class SpriteCache {
	private HashMap sprites;
	
	public SpriteCache() {
		sprites = new HashMap();
	}
	
	private BufferedImage loadImage(String nombre) {
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

	/**
	 * Este método devuelve un objeto que representa el sprite
	 * @param nombre
	 * @return
	 */
	public BufferedImage getSprite(String nombre) {
		BufferedImage img = (BufferedImage)sprites.get(nombre);
		if (img == null) {
			img = loadImage("david/gomera/invaders/images/"+nombre);
			sprites.put(nombre,img);
		}
		return img;
	}
}
