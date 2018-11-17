package david.gomera.invaders.versiones.v27;

public class Monster extends Actor {
	protected int vx;
	protected static final double FIRING_FREQUENCY = 0.01;
	
	public Monster(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"bicho/bicho0.gif","bicho/bicho1.gif"});
		setFrameSpeed(35);
	}
	
	public void act() {
		super.act();
		x+=vx;
		if (x < 0 || x > Stage.WIDTH)
		  vx = -vx;
		if (Math.random()<FIRING_FREQUENCY)
		  fire();
	}

	public int getVx() { return vx; }
	public void setVx(int i) {vx = i;	}
	
	public void collision(Actor a) {
		if (a instanceof Bullet || a instanceof Bomb) {
		  remove();
		  stage.getSoundCache().playSound("explosion.wav");
		  spawn();
		  stage.getPlayer().addScore(20);
		}
	}
	
	public void spawn() {
		Monster m = new Monster(stage);
		m.setX( (int)(Math.random()*Stage.WIDTH) );
    m.setY( (int)(Math.random()*Stage.PLAY_HEIGHT/2) );
	  m.setVx( (int)(Math.random()*20-10) );
	  stage.addActor(m);
	}
	
	public void fire() {
		Laser m = new Laser(stage);
		m.setX(x+getWidth()/2);
		m.setY(y + getHeight());
		stage.addActor(m);
		stage.getSoundCache().playSound("photon.wav");

	}
}
