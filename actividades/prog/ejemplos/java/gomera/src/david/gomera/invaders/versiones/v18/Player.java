package david.gomera.invaders.versiones.v18;

import java.awt.event.KeyEvent;

/**
 * Es el actor que representa al jugador de la partida. 
 */
public class Player extends Actor {
	protected static final int PLAYER_SPEED = 4;
	protected int vx;
	protected int vy;
	private boolean up,down,left,right;
		
	
	public Player(Stage stage) {
		super(stage);
		setSpriteNames( new String[] {"nave.gif"});
	}
	
	public void act() {
		super.act();
		x+=vx;
		y+=vy;
		if (x < 0 || x > Stage.WIDTH)
		  vx = -vx;
		if (y < 0 || y > Stage.HEIGHT)
		  vy = -vy;
	}

	public int getVx() { return vx; }
	public void setVx(int i) {vx = i;	}
 	public int getVy() { return vy; }
    public void setVy(int i) {vy = i;	}
  
  
  protected void updateSpeed() {
  	vx=0;vy=0;
  	if (down) vy = PLAYER_SPEED;
  	if (up) vy = -PLAYER_SPEED;
  	if (left) vx = -PLAYER_SPEED;
  	if (right) vx = PLAYER_SPEED;
  }
  
  public void keyReleased(KeyEvent e) {
   	switch (e.getKeyCode()) {
  		case KeyEvent.VK_DOWN : down = false;break;
		  case KeyEvent.VK_UP : up = false; break;
		  case KeyEvent.VK_LEFT : left = false; break; 
		  case KeyEvent.VK_RIGHT : right = false;break;
   	}
   	updateSpeed();
  }
  
  public void keyPressed(KeyEvent e) {
  	switch (e.getKeyCode()) {
		  case KeyEvent.VK_UP : up = true; break;
		  case KeyEvent.VK_LEFT : left = true; break;
		  case KeyEvent.VK_RIGHT : right = true; break;
		  case KeyEvent.VK_DOWN : down = true;break;
		  case KeyEvent.VK_SPACE : fire(); break;
  	}
  	updateSpeed();
  }
  
  public void fire() {
   	Bullet b = new Bullet(stage);
   	b.setX(x);
   	b.setY(y - b.getHeight());
   	stage.addActor(b);
  }
  
}
