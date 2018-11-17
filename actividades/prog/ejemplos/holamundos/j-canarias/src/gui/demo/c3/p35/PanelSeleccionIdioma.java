package gui.demo.c3.p35;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
//import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelSeleccionIdioma extends JPanel {
	final static long serialVersionUID=1L;
	
	public static final int IDIOMA_ORIGEN = 0;
	public static final int IDIOMA_DESTINO = 1;
	
	private Traductor traductor;
	
	private int tipoIdioma;
	private PanelTraductor panelTraductor;
	
	public PanelSeleccionIdioma(int tipoIdioma, Traductor traductor, PanelTraductor panelTraductor) {
		this.panelTraductor = panelTraductor;
		this.tipoIdioma = tipoIdioma;
		this.traductor = traductor;
		String cadenaTipo;
		
		if (tipoIdioma==IDIOMA_ORIGEN) {
			cadenaTipo = " Idioma Origen ";
		} else {
			cadenaTipo = " Idioma Destino ";
		}
		
		this.setBorder(BorderFactory.createTitledBorder(cadenaTipo));
		this.setMinimumSize(new Dimension(110,50));
		this.setPreferredSize(new Dimension(110,50));
		
		ButtonGroup grupoBotones = new ButtonGroup();
		this.setLayout(new GridLayout(Traductor.NUM_IDIOMAS,1,3,3));
		
		for(int i=0;i<Traductor.NUM_IDIOMAS;i++) {
			JRadioButton boton = new JRadioButton(Traductor.getCadenaIdioma(i));
			this.add(boton);
			final int numBoton = i;
			
			boton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange()==ItemEvent.SELECTED) 
					{
						botonRadioSeleccionado(numBoton); 
					}
				}
			});
			
			grupoBotones.add(boton);
			if (tipoIdioma==IDIOMA_ORIGEN && i==traductor.getIdiomaOrigen()) {
				boton.setSelected(true);
			} else if (tipoIdioma==IDIOMA_DESTINO && i==traductor.getIdiomaDestino()) {
				boton.setSelected(true);
			}
		}
	}
	
	public void botonRadioSeleccionado(int numBoton) {
		if (this.tipoIdioma==IDIOMA_ORIGEN) {
			traductor.setIdiomaOrigen(numBoton);
		} else if (this.tipoIdioma==IDIOMA_DESTINO) {
			traductor.setIdiomaDestino(numBoton);
		} else {
			throw new Error();
		}
		this.panelTraductor.muestraIdiomas();
	}
}
