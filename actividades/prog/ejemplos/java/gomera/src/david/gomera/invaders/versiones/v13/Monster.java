package david.gomera.invaders.versiones.v13;

public class Monster extends Actor {
	protected int vx;
	
	public Monster(Stage stage) {
		super(stage);
		setSpriteName("bicho/bicho.gif");
	}
	
	public void act() {
		x+=vx;
		if (x < 0 || x > Stage.WIDTH)
		  vx = -vx;
	}

	public int getVx() { return vx; }
	public void setVx(int i) {vx = i;	}

}
