package Contrib.oreilly.swing;

//  BasicJogShuttleUI.java
// A UI class for our custom JogShuttle component.
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class BasicJogShuttleUI extends JogShuttleUI
    implements MouseListener, MouseMotionListener
{
    private static final int KNOB_DISPLACEMENT = 3;
    private static final int FINGER_SLOT_DISPLACEMENT = 15;

    private double lastAngle;  // Used to track mouse drags.

    public static ComponentUI createUI(JComponent c) {
        return new BasicJogShuttleUI();
    }

    public void installUI(JComponent c) {
        JogShuttle shuttle = (JogShuttle)c;
        shuttle.addMouseListener(this);
        shuttle.addMouseMotionListener(this);
    }

    public void uninstallUI(JComponent c) {
        JogShuttle shuttle = (JogShuttle)c;
        shuttle.removeMouseListener(this);
        shuttle.removeMouseMotionListener(this);
    }

    public void paint(Graphics g, JComponent c) {
        //  We don't want to paint inside the insets or borders.
        Insets insets = c.getInsets();
        g.translate(insets.left, insets.top);
        int width = c.getWidth() - insets.left - insets.right;
        int height = c.getHeight() - insets.top - insets.bottom;

        //  Draw the outside circle.
        g.setColor(c.getForeground());
        g.fillOval(0, 0, width, height);

        Insets d = ((JogShuttle)c).getDialInsets();
        int value = ((JogShuttle)c).getValue();
        int valuePerRevolution = ((JogShuttle)c).getValuePerRevolution();

        //  Draw the edge of the dial.
        g.setColor(Color.darkGray);
        g.fillOval(d.left, d.top, width-(d.right*2), height-(d.bottom*2));

        //  Draw the inside of the dial.
        g.setColor(Color.gray);
        g.fillOval(d.left + KNOB_DISPLACEMENT,
                   d.top + KNOB_DISPLACEMENT,
                   width - (d.right + d.left) - KNOB_DISPLACEMENT * 2,
                   height - (d.bottom + d.top) - KNOB_DISPLACEMENT * 2);

        //  Draw the finger slot.
        drawFingerSlot(g, c, value, width, height, valuePerRevolution,
                FINGER_SLOT_DISPLACEMENT - 1,
                (double)(width/2) - d.right - FINGER_SLOT_DISPLACEMENT,
                (double)(height/2) - d.bottom - FINGER_SLOT_DISPLACEMENT);

        g.translate(-insets.left, -insets.top);
    }

    private void drawFingerSlot(Graphics g, JComponent c, int value,
        int width, int height, int valuePerRevolution, int size,
        double xradius, double yradius) {

        int currentPosition = value % valuePerRevolution;

        //  Obtain the current angle in radians.
        double angle = ((double)currentPosition / valuePerRevolution) *
                         java.lang.Math.PI * 2;

        // Obtain the X and Y coordinates of the finger slot, with the
        // minimum value at twelve o'clock.
        angle -= (java.lang.Math.PI / 2);
        int xPosition = (int) (xradius * java.lang.Math.sin(angle));
        int yPosition = (int) (yradius * java.lang.Math.cos(angle));
        xPosition = (width / 2) - xPosition;
        yPosition = (height / 2) + yPosition;

        //  Draw the finger slot with a crescent shadow on the top left.
        g.setColor(Color.darkGray);
        g.fillOval(xPosition-(size/2), yPosition-(size/2), size, size);
        g.setColor(Color.lightGray);
        g.fillOval(xPosition-(size/2) + 1, yPosition - (size/2) + 1,
                   size - 1, size - 1);

    }

    // Figure out angle at which a mouse event occurred with respect to the
    // center of the component for intuitive dial dragging.
    protected double calculateAngle(MouseEvent e) {
        int x = e.getX() - ((JComponent)e.getSource()).getWidth() / 2;
        int y = -e.getY() + ((JComponent)e.getSource()).getHeight() / 2;
        if (x == 0) {  // Handle case where math would blow up.
            if (y == 0) {
                return lastAngle;   // Can't tell...
            }
            if (y > 0) {
                return Math.PI / 2;
            }
            return -Math.PI / 2;
        }
        return Math.atan((double)y / (double)x);
    }

    public void mousePressed(MouseEvent e) { lastAngle = calculateAngle(e); }
    public void mouseReleased(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

    // Figure out the change in angle over which the user has dragged,
    // expressed as a fraction of a revolution.
    public double angleDragged(MouseEvent e) {
        double newAngle = calculateAngle(e);
        double change = (lastAngle - newAngle) / Math.PI;
        if (Math.abs(change) > 0.5) {  // Handle crossing origin.
            if (change < 0.0) {
                change += 1.0;
            } else {
                change -= 1.0;
            }
        }

        lastAngle = newAngle;
        return change;
    }

    public void mouseDragged(MouseEvent e) {
        JogShuttle theShuttle = (JogShuttle)e.getComponent();
        theShuttle.setValue(theShuttle.getValue() +
            (int)(angleDragged(e) * theShuttle.getValuePerRevolution()));
    }

    public void mouseMoved(MouseEvent e) { }
}
