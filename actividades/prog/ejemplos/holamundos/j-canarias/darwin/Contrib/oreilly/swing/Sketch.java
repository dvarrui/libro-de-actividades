package Contrib.oreilly.swing;

//  Sketch.java
// A sketching application with two dials: one for horizontal movement, one
// for vertical movement.  The dials are instances of the JogShuttle class.
//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Sketch extends JPanel
    implements PropertyChangeListener, ActionListener
{
    JogShuttle shuttle1;
    JogShuttle shuttle2;
    JPanel board;
    JButton clear;

    int lastX, lastY;  // Keep track of the last point we drew.

    public Sketch() {
        super(true);

        setLayout(new BorderLayout());
        board = new JPanel(true);
        board.setPreferredSize(new Dimension(300, 300));
        board.setBorder(new LineBorder(Color.black, 5));

        clear = new JButton("Clear Drawing Area");
        clear.addActionListener(this);

        shuttle1 = new JogShuttle(0, 300, 150);
        lastX = shuttle1.getValue();
        shuttle2 = new JogShuttle(0, 300, 150);
        lastY = shuttle2.getValue();

        shuttle1.setValuePerRevolution(100);
        shuttle2.setValuePerRevolution(100);

        shuttle1.addPropertyChangeListener(this);
        shuttle2.addPropertyChangeListener(this);

        shuttle1.setBorder(new BevelBorder(BevelBorder.RAISED));
        shuttle2.setBorder(new BevelBorder(BevelBorder.RAISED));

        add(board, BorderLayout.NORTH);
        add(shuttle1, BorderLayout.WEST);
        add(clear, BorderLayout.CENTER);
        add(shuttle2, BorderLayout.EAST);
    }

    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName() == "value") {
            Graphics g = board.getGraphics();
            g.setColor(getForeground());
            g.drawLine(lastX, lastY,
                       shuttle1.getValue(), shuttle2.getValue());
            lastX = shuttle1.getValue();
            lastY = shuttle2.getValue();
        }
    }

    public void actionPerformed(ActionEvent e) {
        //  The button must have been pressed.
        Insets insets = board.getInsets();
        Graphics g = board.getGraphics();
        g.setColor(board.getBackground());
        g.fillRect(insets.left, insets.top,
                   board.getWidth()-insets.left-insets.right,
                   board.getHeight()-insets.top-insets.bottom);
    }

    public static void main(String[] args) {
        UIManager.put(JogShuttleUI.UI_CLASS_ID, "BasicJogShuttleUI");
        Sketch s = new Sketch();
        JFrame frame = new JFrame("Sample Sketch Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(s);
        frame.pack();
        frame.setVisible(true);
    }
}
