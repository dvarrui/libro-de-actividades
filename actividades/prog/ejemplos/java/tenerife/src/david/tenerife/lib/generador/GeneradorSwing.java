package david.tenerife.lib.generador;

import java.io.PrintWriter;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Parametros;
import david.tenerife.lib.Tabla;
import david.tenerife.templates.ArgumentoJet;
import david.tenerife.templates.swing.EditarTemplate;
import david.tenerife.templates.swing.IVentanaTemplate;
import david.tenerife.templates.swing.ListadoTemplate;
import david.tenerife.templates.swing.MenuPrincipalTemplate;
import david.tenerife.templates.swing.NuevoTemplate;
import david.tenerife.templates.swing.TableModelTemplate;
import david.tenerife.util.Ficheros;
import david.tenerife.util.Notacion;

/**
 * Generador de código Java
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060107
 */
public class GeneradorSwing {
	private BaseDatos bd;

	private Parametros param;

	public GeneradorSwing(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	public void generarCodigo() {
		generarTableModel();
		generarInterfazVentana();
		generarMenuPrincipal();
		generarListado();
		generarNuevo();
		generarEditar();
	}

	private void generarEditar() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava() + "/swing/editar";

			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				arg.setTabla(t);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/FrmEditar"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				// Escribir plantilla JET
				EditarTemplate p = new EditarTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	private void generarInterfazVentana() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava() + "/swing";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			// Crear fichero swing
			fich = new PrintWriter(ruta + "/IVentana.java");
			// Escribir plantilla JET
			IVentanaTemplate p = new IVentanaTemplate();
			fich.println(p.generate(arg));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	private void generarListado() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava() + "/swing/listado";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				arg.setTabla(t);

				// Crear fichero swing
				fich = new PrintWriter(ruta + "/FrmListado"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				// Escribir plantilla JET
				ListadoTemplate p = new ListadoTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	private void generarNuevo() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava() + "/swing/nuevo";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				arg.setTabla(t);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/FrmNuevo"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				// Escribir plantilla JET
				NuevoTemplate p = new NuevoTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	private void generarMenuPrincipal() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + param.getValor(Parametros.PROYECTO)
					+ "/swing/menu";
			Ficheros.crearRuta(ruta);

			fich = new PrintWriter(ruta + "/FrmMenuPrincipal.java");
			// Escribir plantilla JET
			MenuPrincipalTemplate t = new MenuPrincipalTemplate();
			fich.println(t.generate(bd));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar las clases TableModel para cada tabla plano
	 */
	private void generarTableModel() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava()
					+ "/swing/TableModel";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				arg.setTabla(t);

				// Crear fichero java para el Almacén
				fich = new PrintWriter(ruta + "/TableModel"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				// Escribir plantilla JET
				TableModelTemplate p = new TableModelTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

}