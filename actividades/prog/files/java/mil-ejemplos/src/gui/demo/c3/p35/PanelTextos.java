package gui.demo.c3.p35;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelTextos extends JPanel {
	final static long serialVersionUID = 1L;
	
	private Traductor traductor;
	
	private JLabel etiquetaOrigen;
	private JLabel etiquetaDestino;
	private JTextArea areaOrigen;
	private JTextArea areaDestino;
	private JButton botonTraduccion;
	
	public PanelTextos(Traductor traductor) {
		this.traductor = traductor;
		this.etiquetaOrigen = new JLabel();
		this.etiquetaDestino = new JLabel();
		this.muestraIdiomas();
		
		this.areaOrigen = new JTextArea("Escriba el texto aquí",2,20);
		this.areaDestino = new JTextArea("Aquí aparecerá la traducción",2,20);
		this.areaDestino.setEditable(false);
		
		botonTraduccion = new JButton("Traducir");
		botonTraduccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traducir();
			}
		});
		this.setBorder(BorderFactory.createTitledBorder(" Traducción "));
		
		JScrollPane spAreaOrigen = new JScrollPane(areaOrigen);
		JScrollPane spAreaDestino = new JScrollPane(areaDestino);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints config1 = new GridBagConstraints();
		config1.insets = new Insets(3,3,3,3);
		config1.anchor = GridBagConstraints.WEST;
		this.add(etiquetaOrigen, config1);
		
		GridBagConstraints config2 = new GridBagConstraints();
		config2.insets = new Insets(3,3,3,3);
		config2.anchor = GridBagConstraints.WEST;
		config2.gridy = 2;
		this.add(etiquetaDestino,config2);
		
		GridBagConstraints config3 = new GridBagConstraints();
		config3.insets = new Insets(3,3,3,3);
		config3.gridy = 4;
		this.add(botonTraduccion,config3);
		
		GridBagConstraints config4 = new GridBagConstraints();
		config4.insets = new Insets(3,3,3,3);
		config4.weighty = 1.0;
		config4.weightx = 1.0;
		config4.fill = GridBagConstraints.BOTH;		
		config4.gridy = 1;
		this.add(spAreaOrigen,config4);
		
		GridBagConstraints config5 = new GridBagConstraints();
		config5.insets = new Insets(3,3,3,3);
		config5.weighty = 1.0;
		config5.weightx = 1.0;
		config5.fill = GridBagConstraints.BOTH;		
		config5.gridy = 3;
		this.add(spAreaDestino,config5);
	}
	
	public void traducir() {
		areaDestino.setText(traductor.traduceTexto(areaOrigen.getText()));
	}
	
	public void muestraIdiomas() {
		this.etiquetaOrigen.setText(" Idioma "+Traductor.getCadenaIdioma(traductor.getIdiomaOrigen()));
		this.etiquetaDestino.setText(" Idioma "+Traductor.getCadenaIdioma(traductor.getIdiomaDestino()));
	}
}
