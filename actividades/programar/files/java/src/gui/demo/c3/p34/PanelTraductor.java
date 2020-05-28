package gui.demo.c3.p34;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class PanelTraductor extends JPanel{
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JButton btnTraduccion;
	private JTextArea txtTextoOrigen;
	private JTextArea txtTextoDestino;
	private JLabel lbIdiomaOrigen;
	private JLabel lbIdiomaDestino;
	private JRadioButton rbSentidoOriginal;
	private JRadioButton rbSentidoInvertido;
	
	//private final int idiomaOrigenOriginal;
	//private final int idiomaDestinoOriginal;
	
	public PanelTraductor(Traductor traductor) {
		this.traductor=traductor;
		//this.idiomaOrigenOriginal=traductor.getIdiomaOrigen();
		//this.idiomaDestinoOriginal=traductor.getIdiomaDestino();
		this.btnTraduccion=new JButton("Traducir");
		this.txtTextoOrigen=new JTextArea("Escriba aquí");
		this.txtTextoDestino=new JTextArea();
		this.txtTextoDestino.setEditable(false);
		
		JScrollPane scrollTextoOrigen=new JScrollPane(txtTextoOrigen);
		JScrollPane scrollTextoDestino=new JScrollPane(txtTextoDestino);
		scrollTextoOrigen.setPreferredSize(new Dimension(140,100));
		scrollTextoDestino.setPreferredSize(new Dimension(140,100));		
		
		this.lbIdiomaOrigen=new JLabel();
		this.lbIdiomaDestino=new JLabel();
		muestraIdiomasEnTextos();
		
		this.rbSentidoOriginal=new JRadioButton(
				Traductor.getCadenaIdioma(traductor.getIdiomaOrigen())
				+" - "
				+Traductor.getCadenaIdioma(traductor.getIdiomaDestino()));
		
		this.rbSentidoInvertido=new JRadioButton(
				Traductor.getCadenaIdioma(traductor.getIdiomaDestino())
				+" - "
				+Traductor.getCadenaIdioma(traductor.getIdiomaOrigen()));
	
		ButtonGroup grupoBotones=new ButtonGroup();
		grupoBotones.add(rbSentidoOriginal);
		grupoBotones.add(rbSentidoInvertido);
		rbSentidoOriginal.setSelected(true);
		
		rbSentidoOriginal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rbSentidoOriginal.isSelected()) {
					invierteIdioma();
				}
			}
		});
		rbSentidoInvertido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rbSentidoInvertido.isSelected()) {
					invierteIdioma();
				}
			}
		});
		
		this.btnTraduccion.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traducir();
			}
		});
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints config1=new GridBagConstraints();
		config1.insets=new Insets(3,3,3,3);
		config1.anchor=GridBagConstraints.SOUTHWEST;
		config1.gridx=0;
		config1.gridy=0;
		config1.weightx=1.0;
		this.add(lbIdiomaOrigen,config1);
		
		GridBagConstraints config2=new GridBagConstraints();
		config2.insets=new Insets(3,3,3,3);
		config2.anchor=GridBagConstraints.SOUTHWEST;
		config2.gridx=1;
		config2.gridy=0;
		config2.weightx=1.0;
		this.add(lbIdiomaDestino,config2);
		
		GridBagConstraints config3=new GridBagConstraints();
		config3.insets=new Insets(3,3,3,3);
		config3.anchor=GridBagConstraints.SOUTHWEST;
		config3.gridx=0;
		config3.gridy=1;
		config3.weightx=1.0;
		config3.weighty=1.0;
		config3.fill=GridBagConstraints.BOTH;
		this.add(scrollTextoOrigen,config3);

		GridBagConstraints config4=new GridBagConstraints();
		config4.insets=new Insets(3,3,3,3);
		config4.gridx=1;
		config4.gridy=1;
		config4.weightx=1.0;
		config4.weighty=1.0;
		config4.fill=GridBagConstraints.BOTH;
		this.add(scrollTextoDestino,config4);
		
		GridBagConstraints config5=new GridBagConstraints();
		config5.insets=new Insets(3,3,3,3);
		config5.gridx=0;
		config5.gridy=2;
		config5.weightx=1.0;
		config5.weighty=0;
		config5.gridwidth=2;
		this.add(btnTraduccion,config5);
		
		GridBagConstraints config6=new GridBagConstraints();
		config6.insets=new Insets(3,3,3,3);
		config6.gridx=0;
		config6.gridy=3;
		config6.weightx=1.0;
		config6.weighty=0;
		config6.gridwidth=2;
		this.add(new JLabel("Sentido de la traducción"),config6);

		GridBagConstraints config7=new GridBagConstraints();
		config7.insets=new Insets(3,3,3,3);
		config7.gridx=0;
		config7.gridy=4;
		config7.weightx=1.0;
		config7.weighty=0;
		config7.gridwidth=2;
		this.add(rbSentidoOriginal,config7);
		
		GridBagConstraints config8=new GridBagConstraints();
		config8.insets=new Insets(3,3,3,3);
		config8.gridx=0;
		config8.gridy=6;
		config8.weightx=1.0;
		config8.weighty=0;
		config8.gridwidth=2;
		this.add(rbSentidoInvertido,config8);
	}

	protected void invierteIdioma() {
		this.traductor.invierteIdioma();
		this.muestraIdiomasEnTextos();
	}
	
	private void traducir() {
		txtTextoDestino.setText(traductor.traduceTexto(txtTextoOrigen.getText()));
	}
	
	private void muestraIdiomasEnTextos() {
		this.lbIdiomaOrigen.setText("Texto en "+Traductor.getCadenaIdioma(traductor.getIdiomaOrigen()));
		this.lbIdiomaDestino.setText("Texto en "+Traductor.getCadenaIdioma(traductor.getIdiomaDestino()));
	}
}
