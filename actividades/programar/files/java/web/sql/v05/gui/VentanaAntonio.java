/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */

package web.sql.v05.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

//import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import java.awt.event.ActionListener;

import web.sql.v05.sql.*;

import java.util.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class VentanaAntonio extends javax.swing.JFrame implements
		ActionListener {
	public static final long serialVersionUID = 1;

	// Atributos visuales
	private JPanel pnlPrincipal;

	private JButton btnAnterior;

	private JTextField txtCodigo;

	private JButton btnRefrescar;

	private JLabel lblCodigo;

	private JButton btnActualizar;

	private JTextField txtApellido2;

	private JButton btnBorrar;

	private JTextField txtApellido1;

	private JLabel lblApellido2;

	private JLabel lblApellido1;

	private JButton btnNuevo;

	private JTextField txtNombre;

	private JLabel lblNombre;

	private JButton btnSiguiente;

	private JLabel lblNumeroRegistro;

	private JPanel pnlBotones;

	// Atributos NO visuales
	private BaseDatos bd;

	private ArrayList listado;

	private int nro; // Número del registro actual

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		VentanaAntonio inst = new VentanaAntonio();
		inst.setVisible(true);
	}

	public VentanaAntonio() {
		super();
		initGUI(); // Inicializar los componentes visuales
		inicializar(); // Inicializar los componentes NO visuales
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Ventana estilo Antonio");
			{
				pnlPrincipal = new JPanel();
				GridLayout pnlPrincipalLayout = new GridLayout(4, 3);
				pnlPrincipalLayout.setHgap(5);
				pnlPrincipalLayout.setVgap(5);
				pnlPrincipalLayout.setColumns(3);
				pnlPrincipalLayout.setRows(4);
				pnlPrincipal.setLayout(pnlPrincipalLayout);
				getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
				{
					lblCodigo = new JLabel();
					pnlPrincipal.add(lblCodigo);
					lblCodigo.setText("Código");
				}
				{
					txtCodigo = new JTextField();
					pnlPrincipal.add(txtCodigo);
				}
				{
					btnRefrescar = new JButton();
					pnlPrincipal.add(btnRefrescar);
					btnRefrescar.setText("Refrescar");
					btnRefrescar.addActionListener(this);
				}
				{
					lblNombre = new JLabel();
					pnlPrincipal.add(lblNombre);
					lblNombre.setText("Nombre");
				}
				{
					txtNombre = new JTextField();
					pnlPrincipal.add(txtNombre);
				}
				{
					btnNuevo = new JButton();
					pnlPrincipal.add(btnNuevo);
					btnNuevo.setText("Nuevo");
					btnNuevo.addActionListener(this);
				}
				{
					lblApellido1 = new JLabel();
					pnlPrincipal.add(lblApellido1);
					lblApellido1.setText("Apellido1");
				}
				{
					txtApellido1 = new JTextField();
					pnlPrincipal.add(txtApellido1);
				}
				{
					btnActualizar = new JButton();
					pnlPrincipal.add(btnActualizar);
					btnActualizar.setText("Actualizar");
					btnActualizar.addActionListener(this);
				}
				{
					lblApellido2 = new JLabel();
					pnlPrincipal.add(lblApellido2);
					lblApellido2.setText("Apellido2");
				}
				{
					txtApellido2 = new JTextField();
					pnlPrincipal.add(txtApellido2);
				}
				{
					btnBorrar = new JButton();
					pnlPrincipal.add(btnBorrar);
					btnBorrar.setText("Borrar");
					btnBorrar.addActionListener(this);
				}
			}
			{
				pnlBotones = new JPanel();
				FlowLayout pnlBotonesLayout = new FlowLayout();
				pnlBotones.setLayout(pnlBotonesLayout);
				getContentPane().add(pnlBotones, BorderLayout.SOUTH);
				{
					btnAnterior = new JButton();
					pnlBotones.add(btnAnterior);
					btnAnterior.setText("<<");
					btnAnterior.addActionListener(this);
				}
				{
					lblNumeroRegistro = new JLabel();
					pnlBotones.add(lblNumeroRegistro);
					lblNumeroRegistro.setText("0");
				}
				{
					btnSiguiente = new JButton();
					pnlBotones.add(btnSiguiente);
					btnSiguiente.setText(">>");
					btnSiguiente.addActionListener(this);
				}
			}
			pack();
			this.setSize(375, 170);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// listener
	public void actionPerformed(ActionEvent evt) {
		try {
			if (evt.getActionCommand().equals(btnRefrescar.getActionCommand())) {
				System.out.println("Refrescar");
				this.mostrarRegistro();
			} else if (evt.getActionCommand().equals(
					btnNuevo.getActionCommand())) {
				System.out.println("Nuevo");
				Persona p = new Persona();
				p.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
				p.setNombre(this.txtNombre.getText());
				p.setApellido1(this.txtApellido1.getText());
				p.setApellido2(this.txtApellido2.getText());
				if (p.insert(bd.getConexion())) 
					listado.add(p);
				nro = listado.size()-1;
				this.mostrarRegistro();
			} else if (evt.getActionCommand().equals(
					btnBorrar.getActionCommand())) {
				System.out.println("Borrar");
				Persona p = (Persona) listado.get(nro);
				if (p.delete(bd.getConexion())) {
					listado.remove(nro);
					nro =0;
					this.mostrarRegistro();
				}
			} else if (evt.getActionCommand().equals(
					btnActualizar.getActionCommand())) {
				System.out.println("Actualizar");
				Persona p = (Persona) listado.get(nro);
				p.setNombre(this.txtNombre.getText());
				p.setApellido1(this.txtApellido1.getText());
				p.setApellido2(this.txtApellido2.getText());
				p.update(bd.getConexion());
			} else if (evt.getActionCommand().equals(
					btnAnterior.getActionCommand())) {
				System.out.println("Anterior");
				if (nro > 0)
					nro--;
				this.mostrarRegistro();
			} else if (evt.getActionCommand().equals(
					btnSiguiente.getActionCommand())) {
				System.out.println("Siguiente");
				if ((nro + 1) < listado.size())
					nro++;
				this.mostrarRegistro();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// Inicializar
	public void inicializar() {
		bd = new BaseDatos();
		bd.abrir(); // Abrir la base de datos
		listado = Persona.select(bd.getConexion()); // select * from personas

		nro = 0; // Mostrar el registro nro
		this.mostrarRegistro();
	}

	private void mostrarRegistro() {
		// Actualiza número de registros
		this.lblNumeroRegistro.setText("Registro " + (nro + 1) + " de "
				+ listado.size());
		// Muestra el contenido del registro NRO en el formulario
		Persona p = (Persona) listado.get(nro);
		this.txtCodigo.setText("" + p.getCodigo());
		this.txtNombre.setText(p.getNombre());
		this.txtApellido1.setText(p.getApellido1());
		this.txtApellido2.setText(p.getApellido2());
	}

}
