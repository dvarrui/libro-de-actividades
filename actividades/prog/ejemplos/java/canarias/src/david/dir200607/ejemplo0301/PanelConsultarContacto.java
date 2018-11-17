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
import java.awt.Font;

public class PanelConsultarContacto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNombre = null;
	private JLabel lblApellido1 = null;
	private JLabel lblApellido2 = null;
	private JLabel lblTelefono1 = null;
	private JTextField txtNombre = null;
	private JTextField txtApellido1 = null;
	private JTextField txtApellido2 = null;
	private JTextField txtTelefono1 = null;
	private JLabel lblRegistro = null;
	private JButton btnAnterior = null;
	private JButton btnSiguiente = null;
	
	private Agenda miAgenda = null;  //  @jve:decl-index=0:
	private int cursor;
	/**
	 * This is the default constructor
	 */
	public PanelConsultarContacto() {
		super();
		initialize();
		cursor=0;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblRegistro = new JLabel();
		lblRegistro.setBounds(new Rectangle(104, 144, 31, 16));
		lblRegistro.setBackground(new Color(153, 153, 153));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setForeground(new Color(0, 0, 204));
		lblRegistro.setText("0/0");
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
		this.setSize(224, 177);
		this.setLayout(null);
		this.setToolTipText("Agregar nuevos contactos");
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "Consultar contacto", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.add(lblNombre, null);
		this.add(lblApellido1, null);
		this.add(lblApellido2, null);
		this.add(lblTelefono1, null);
		this.add(getTxtNombre(), null);
		this.add(getTxtApellido1(), null);
		this.add(getTxtApellido2(), null);
		this.add(getTxtTelefono1(), null);
		this.add(lblRegistro, null);
		this.add(getBtnAnterior(), null);
		this.add(getBtnSiguiente(), null);
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
			txtNombre.setEditable(false);
			txtNombre.setBackground(Color.white);
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
			txtApellido1.setEditable(false);
			txtApellido1.setBackground(Color.white);
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
			txtApellido2.setEditable(false);
			txtApellido2.setBackground(Color.white);
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
			txtTelefono1.setEditable(false);
			txtTelefono1.setBackground(Color.white);
		}
		return txtTelefono1;
	}

	/**
	 * This method initializes btnAnterior	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAnterior() {
		if (btnAnterior == null) {
			btnAnterior = new JButton();
			btnAnterior.setBounds(new Rectangle(19, 141, 70, 24));
			btnAnterior.setText("<");
			btnAnterior.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ejecutarEventoAnterior();
				}
			});
		}
		return btnAnterior;
	}

	public void setAgenda(Agenda agenda) {
		this.miAgenda = agenda;
	}
	
	private void ejecutarEventoAnterior() {
		if (cursor>0) cursor--;
		if (this.miAgenda.getNumeroContactos()>0) 
			this.mostrarPersona();
	}

	private void ejecutarEventoSiguiente() {
		if (cursor<this.miAgenda.getNumeroContactos()-1) cursor++;
		if (this.miAgenda.getNumeroContactos()>0) 
			this.mostrarPersona();
	}

	private void mostrarPersona() {
		Persona p = (Persona) this.miAgenda.getPersona(cursor);
		this.txtNombre.setText(p.getNombre());
		this.txtApellido1.setText(p.getApellido1());
		this.txtApellido2.setText(p.getApellido2());
		this.txtTelefono1.setText(p.getTelefono1());
		this.lblRegistro.setText(""+(cursor+1)+"/"+this.miAgenda.getNumeroContactos());
	}
	
	public void actualizar() {
		this.mostrarPersona();
	}
	
	/**
	 * This method initializes btnSiguiente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton();
			btnSiguiente.setBounds(new Rectangle(150, 141, 59, 22));
			btnSiguiente.setText(">");
			btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ejecutarEventoSiguiente();
				}
			});
		}
		return btnSiguiente;
	}
}  //  @jve:decl-index=0:visual-constraint="8,15"
