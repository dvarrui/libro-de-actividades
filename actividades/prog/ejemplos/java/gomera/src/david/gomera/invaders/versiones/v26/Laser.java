package david.gomera.invaders.versiones.v26;

public class Laser extends Actor {
	protected static final int BULLET_SPEED=3;
	
	public Laser(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"disparo/disparo0.gif","disparo/disparo1.gif","disparo/disparo2.gif"});
		setFrameSpeed(10);
	}
	
	public void act() {
		super.act();
		y+=BULLET_SPEED;
		if (y > Stage.PLAY_HEIGHT)
		  remove();
	}
}
