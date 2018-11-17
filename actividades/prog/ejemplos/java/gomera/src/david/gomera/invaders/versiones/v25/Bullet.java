package david.gomera.invaders.versiones.v25;

/**
 * Es el actor disparo dentro de la escena
 */
public class Bullet extends Actor {
	protected static final int BULLET_SPEED=10;
	
	public Bullet(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"misil.gif"});
	}
	
	public void act() {
		super.act();
		y-=BULLET_SPEED;
		if (y < 0)
		  remove();
	}
}
