package jaoe.david.splash;

import java.awt.*;
import java.net.URL;

public class SplashScreen extends Window {
	static final long serialVersionUID=1;
	Image displayImage;
	
	public SplashScreen(Image i)
	{
		super(new Frame());
		displayImage = i;
		setupWindow();
	}
	//allows the splash screen to appear on another screen.
	public SplashScreen(Image i, GraphicsConfiguration gc)
	{
		super(new Frame(), gc);
		displayImage = i;
		setupWindow();
	}
	private void setupWindow()
	{
		//Wait until the image is fully loaded
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(displayImage,0);
		try
		{
			mt.waitForID(0);
		}
		catch(InterruptedException ie) {}
		
		
		int width = displayImage.getWidth(this);
		int height =  displayImage.getHeight(this);
		
		setSize(width, height);
		
		setLocationRelativeTo(null);
		Point p = getLocation();
		Dimension d = new Dimension(width, height);
		Rectangle rect = new Rectangle (p, d);
		
		Image background = null;
		
		try
		{
			Robot robot = new Robot(getGraphicsConfiguration().getDevice());
			background = robot.createScreenCapture(rect);
			background.getGraphics().drawImage(displayImage, 0, 0, this);
			displayImage = background;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Could not get a screen capture for this window");
			//Could just ignore it and go ahead drawing the image
			//without the screen captured background.
		}
	}
	public void paint(Graphics g)
	{
		g.drawImage(displayImage, 0, 0, this);
	}
	public void update(Graphics g)
	{
		paint(g);
	}
	
	//------Test Method-------
	public static void main(String[] args)
	{
		System.out.println("Running a test example of this class");
		long timeStart = System.currentTimeMillis();
		
		//URL imageURL = SplashScreen.class.getResource("/home/david/workspace/gomera/src/david/gomera/splash/mygame.png");
		URL imageURL = SplashScreen.class.getResource("mygame.png");
		if (imageURL == null)
		{
			System.err.println("Splash image not found");
			System.exit(0);
		}
		
		Image i = Toolkit.getDefaultToolkit().createImage(imageURL);
		SplashScreen ss = new SplashScreen(i);
		ss.toFront();
		//ss.setAlwaysOnTop(true);  //this is supported in 1.5 and above only.
		ss.setVisible(true);
		long timeTotal = System.currentTimeMillis() - timeStart;
		
		System.out.println("Took "+timeTotal+"ms to create and display the window");
		
		//wait for a while then close.
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted");
		}
		ss.setVisible(false);
		ss.dispose();
		System.exit(0);
	}
	
}