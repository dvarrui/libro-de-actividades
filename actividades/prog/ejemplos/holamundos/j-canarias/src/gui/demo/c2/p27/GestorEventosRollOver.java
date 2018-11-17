package gui.demo.c2.p27;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GestorEventosRollOver implements MouseListener {
	public Color colorAnterior;

	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {
		Component component=e.getComponent();
		component.setForeground(colorAnterior);
	}
	
	public void mouseEntered(MouseEvent e) {
		Component component=e.getComponent();
		colorAnterior=component.getForeground();
		component.setForeground(java.awt.Color.BLUE);
	}
}
