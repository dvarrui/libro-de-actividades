package david.gomera.invaders.versiones.v14;

import java.awt.image.ImageObserver;

public interface Stage extends ImageObserver {
	public static final int WIDTH=640;
	public static final int HEIGHT=480;
	public static final int SPEED=10;
	public SpriteCache getSpriteCache();

}
