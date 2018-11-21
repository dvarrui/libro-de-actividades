package Contrib;


// Noel Enete
// noel@enete.com

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TouchWindow3 extends Applet
      implements MouseMotionListener
      {
      int xMouse;
      int yMouse;
      int xWindow;
      int yWindow;


      public void init ()
        {
        addMouseMotionListener (this);
        }


      public void mouseDragged (MouseEvent evt)
        {
        xMouse = evt.getX ();
        yMouse = evt.getY ();
        repaint ();
        }
      public void mouseMoved (MouseEvent evt)
        {
        xMouse = evt.getX ();
        yMouse = evt.getY ();
        repaint ();
        }


      public void paint (Graphics g)
        {
        xWindow = getSize().width;
        yWindow = getSize().height;
        drawTinkerToys (g, 25);
        }


      void drawTinkerToys (Graphics g,
        int iNumSpokes)
        {
        int i;
        Color c;

        for (i = 0; i < iNumSpokes; i++)
          {
          c = new Color
            (
            (int) (255 * Math.random ()),
            (int) (255 * Math.random ()),
            (int) (255 * Math.random ())
            );
          g.setColor (c);

          g.drawLine
            (
            (int) (xWindow * Math.random ()),
            (int) (yWindow * Math.random ()),
            (int) (xWindow * Math.random ()),
            (int) (yWindow * Math.random ())
            );
          }
        }
      }

