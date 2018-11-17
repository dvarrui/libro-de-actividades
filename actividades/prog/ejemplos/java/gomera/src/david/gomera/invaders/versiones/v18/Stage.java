package david.gomera.invaders.versiones.v18;

import java.awt.image.ImageObserver;

/**
 * Definición de la inteface Stage
 */
public interface Stage extends ImageObserver {
	public static final int WIDTH=640;
	public static final int HEIGHT=480;
	public static final int SPEED=10;
	public SpriteCache getSpriteCache();
	public void addActor(Actor a);
}
