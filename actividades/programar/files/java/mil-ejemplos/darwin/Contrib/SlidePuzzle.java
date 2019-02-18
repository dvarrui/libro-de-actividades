package Contrib;

import java.applet.*;
import java.awt.*;

/* SlidePuzzle, originally from VisualJ++ Developer's News, July 1997 */
public class SlidePuzzle extends Applet implements Runnable {
     Thread m_SlidePuzzle = null;

     private Graphics m_Graphics;
     private Image logo;
     private boolean m_fAllLoaded = false;
     private Rectangle squares[];
     private int order[];
     private int x=1;
     private int y=0;

	public void init()
	{
		 //Define and populate Rectangle array
		 squares=new Rectangle[9];
		 squares[0]=new Rectangle(0,0,60,60);
		 squares[1]=new Rectangle(0,60,60,60);
		 squares[2]=new Rectangle(0,120,60,60);
		 squares[3]=new Rectangle(60,0,60,60);
		 squares[4]=new Rectangle(60,60,60,60);
		 squares[5]=new Rectangle(60,120,60,60);
		 squares[6]=new Rectangle(120,0,60,60);
		 squares[7]=new Rectangle(120,60,60,60);
		 squares[8]=new Rectangle(120,120,60,60);

		 //Define and populate int array
		 order = new int[9];
		 order[0]=6;
		 order[1]=2;
		 order[2]=8;
		 order[3]=1;
		 order[4]=0;
		 order[5]=7;
		 order[6]=5;
		 order[7]=4;
		 order[8]=3;
	}

	public void paint(Graphics g)
	{
		 if (m_fAllLoaded)
		 {
			  Rectangle r = g.getClipRect();
			  g.clearRect(r.x,r.y,r.width,r.height);
			  g.drawImage(logo,0,0,null);

	}      else
			  g.drawString("Loading images...", 10, 20);

	}

	public void start()
	{
		 if (m_SlidePuzzle == null)
		 {
			  m_SlidePuzzle = new Thread(this);
			  m_SlidePuzzle.start();
		 }
	}

	public void stop()
	{
		 if (m_SlidePuzzle != null)
		 {
			  m_SlidePuzzle.stop();
			  m_SlidePuzzle = null;
		 }
	}

	public void run()
	{
		 if (!m_fAllLoaded)
		 {
			m_Graphics = getGraphics();
			MediaTracker tracker = new MediaTracker(this);
			logo = getImage(getDocumentBase(),"vjlogo.gif");
			tracker.addImage(logo, 0);

			  try
			  {
				   tracker.waitForAll();
				   m_fAllLoaded = !tracker.isErrorAny();
			  }
			  catch (InterruptedException e)
			  {
			  }

			  if (!m_fAllLoaded)
			  {
			   stop();
			   m_Graphics.drawString("Error loading images!", 10, 40);
			   return;
			  }
		 }

	//Clear the screen and draw first image
	repaint();

	//Move the squares
	while(true)
	{
		 for (x=1;x<9;x++)
		 {
		 //Slow down the animation
		 try
		 {Thread.sleep(350);}
		 catch (InterruptedException e)
			  {}

	//Move square to next location

	m_Graphics.copyArea(squares[order[x]].x,
	squares[order[x]].y,60,60,
	squares[order[x-1]].x-
	squares[order[x]].x,squares[order[x-1]].y-
	squares[order[x]].y);

	//Clear most recently copied square

	m_Graphics.clearRect(squares[order[x]].x,
	squares[order[x]].y,60,60);
		 }

	//Repaint original graphic after two cycles
		 y++;
		 if(y==2)
		 {
		 repaint();
		 y=0;
		 }
	   }
	}
}
