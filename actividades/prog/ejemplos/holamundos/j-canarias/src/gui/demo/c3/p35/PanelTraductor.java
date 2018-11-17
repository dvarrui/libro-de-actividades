package gui.demo.c3.p35;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class PanelTraductor extends JPanel{
	static final long serialVersionUID=1;
	
	private PanelTextos panelTextos;
	private PanelSeleccionIdioma panelIdiomaOrigen;
	private PanelSeleccionIdioma panelIdiomaDestino;

	public PanelTraductor(Traductor traductor) {
		panelTextos = new PanelTextos(traductor);
		panelIdiomaOrigen = new PanelSeleccionIdioma(PanelSeleccionIdioma.IDIOMA_ORIGEN,traductor,this);
		panelIdiomaDestino = new PanelSeleccionIdioma(PanelSeleccionIdioma.IDIOMA_DESTINO,traductor,this);
		this.setLayout(new GridBagLayout());
	
	
		GridBagConstraints config1 = new GridBagConstraints();
		config1.insets = new Insets(3,3,3,3);
		config1.weighty = 1.0;
		config1.weightx = 1.0;
		config1.fill = GridBagConstraints.BOTH;
		config1.gridheight = 2;
		this.add(panelTextos, config1);
		
		GridBagConstraints config2 = new GridBagConstraints();
		config2.insets = new Insets(3,3,3,3);
		config2.gridx = 1;
		config2.weighty = 1.0;
		config2.fill = GridBagConstraints.BOTH;
		this.add(panelIdiomaOrigen, config2);
		
		GridBagConstraints config3 = new GridBagConstraints();
		config3.insets = new Insets(3,3,3,3);
		config3.gridx = 1;
		config3.gridy = 1;
		config3.weighty = 1.0;
		config3.fill = GridBagConstraints.BOTH;
		this.add(panelIdiomaDestino, config3);
	}

	public void muestraIdiomas() {
		this.panelTextos.muestraIdiomas();
	}
}
