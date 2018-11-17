package david.dir200607.ejemplo0301;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class PanelAgregarContacto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNombre = null;
	private JLabel lblApellido1 = null;
	private JLabel lblApellido2 = null;
	private JLabel lblTelefono1 = null;
	private JTextField txtNombre = null;
	private JTextField txtApellido1 = null;
	private JTextField txtApellido2 = null;
	private JTextField txtTelefono1 = null;
	private JLabel lblRegistros = null;
	private JButton btnAgregar = null;

	private Agenda miAgenda = null;
	/**
	 * This is the default constructor
	 */
	public PanelAgregarContacto() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblRegistros = new JLabel();
		lblRegistros.setBounds(new Rectangle(18, 171, 193, 16));
		lblRegistros.setBackground(new Color(153, 153, 153));
		lblRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistros.setForeground(new Color(0, 0, 204));
		lblRegistros.setText("Número de registros: 0");
		lblTelefono1 = new JLabel();
		lblTelefono1.setBounds(new Rectangle(17, 115, 74, 15));
		lblTelefono1.setText("Teléfono1");
		lblApellido2 = new JLabel();
		lblApellido2.setBounds(new Rectangle(16, 85, 75, 15));
		lblApellido2.setText("Apellido2");
		lblApellido1 = new JLabel();
		lblApellido1.setBounds(new Rectangle(18, 60, 72, 15));
		lblApellido1.setText("Apellido1");
		lblNombre = new JLabel();
		lblNombre.setBounds(new Rectangle(17, 30, 74, 17));
		lblNombre.setText("Nombre");
		this.setSize(224, 199);
		this.setLayout(null);
		this.setToolTipText("Agregar nuevos contactos");
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "Agregar contacto", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		this.add(lblNombre, null);
		this.add(lblApellido1, null);
		this.add(lblApellido2, null);
		this.add(lblTelefono1, null);
		this.add(getTxtNombre(), null);
		this.add(getTxtApellido1(), null);
		this.add(getTxtApellido2(), null);
		this.add(getTxtTelefono1(), null);
		this.add(lblRegistros, null);
		this.add(getBtnAgregar(), null);
	}

	/**
	 * This method initializes txtNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(new Rectangle(104, 28, 106, 19));
			txtNombre.setBackground(new Color(255, 255, 204));
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtApellido1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtApellido1() {
		if (txtApellido1 == null) {
			txtApellido1 = new JTextField();
			txtApellido1.setBounds(new Rectangle(105, 58, 104, 18));
			txtApellido1.setBackground(new Color(255, 255, 204));
		}
		return txtApellido1;
	}

	/**
	 * This method initializes txtApellido2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtApellido2() {
		if (txtApellido2 == null) {
			txtApellido2 = new JTextField();
			txtApellido2.setBounds(new Rectangle(105, 86, 103, 19));
			txtApellido2.setBackground(new Color(255, 255, 204));
		}
		return txtApellido2;
	}

	/**
	 * This method initializes txtTelefono1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTelefono1() {
		if (txtTelefono1 == null) {
			txtTelefono1 = new JTextField();
			txtTelefono1.setBounds(new Rectangle(103, 114, 106, 19));
			txtTelefono1.setBackground(new Color(255, 255, 204));
		}
		return txtTelefono1;
	}

	/**
	 * This method initializes btnAgregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAgregar() {
		if (btnAgregar == null) {
			btnAgregar = new JButton();
			btnAgregar.setBounds(new Rectangle(19, 141, 188, 24));
			btnAgregar.setText("Agregar contacto");
			btnAgregar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ejecutarEventoAgregar();
				}
			});
		}
		return btnAgregar;
	}

	public void setAgenda(Agenda agenda) {
		this.miAgenda = agenda;
	}
	
	private void ejecutarEventoAgregar() {
		Persona p = new Persona();
		p.setNombre(txtNombre.getText());
		p.setApellido1(txtApellido1.getText());
		p.setApellido2(txtApellido2.getText());
		p.setTelefono1(txtTelefono1.getText());
		
		miAgenda.agregarContacto(p);
		this.lblRegistros.setText("Número de registros: "+this.miAgenda.getNumeroContactos());
		
		this.txtNombre.setText(null);
		this.txtApellido1.setText(null);
		this.txtApellido2.setText(null);
		this.txtTelefono1.setText(null);
	}
}  //  @jve:decl-index=0:visual-constraint="8,15"
