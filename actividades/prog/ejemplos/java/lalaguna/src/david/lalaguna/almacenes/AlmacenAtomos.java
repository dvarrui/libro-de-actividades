package david.lalaguna.almacenes;

import david.lalaguna.entidades.IEntidad;
import david.lalaguna.estructuras.Atomo;
import david.lalaguna.estructuras.IEstructura;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenAtomos implements IAlmacen {
	public static final String NOMBRETABLA = "atomos";

	private Conexion conexion;

	/**
	 * Añade un nuevo átomo al almacén.
	 */
	public boolean add(IEstructura estructura) {
		Atomo registro = (Atomo) estructura;
		Statement st;
		try {
			st = conexion.getConnection().createStatement();
			st.executeUpdate("INSERT INTO " + NOMBRETABLA
					+ " (cod_atomo,atomo,descripcion) " + "VALUES ('"
					+ registro.getCodAtomo() + "'" + ",'" + registro.getAtomo()
					+ "'" + ",'" + registro.getDescripcion() + "'" + ");");
			st.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return true;
	}

	public boolean close() {
		return conexion.close();
	}

	public boolean delete(IEstructura estructura) {
		Atomo registro = (Atomo) estructura;
		Statement st;
		try {
			st = conexion.getConnection().createStatement();
			st.executeUpdate("DELETE " + NOMBRETABLA + " WHERE " + "atomo='"
					+ registro.getAtomo() + "'" + ";");
			st.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return true;
	}

	public ArrayList getAll() {
		Statement st;
		ResultSet rs;
		ArrayList lista = new ArrayList();
		Atomo reg;

		try {
			st = conexion.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM " + NOMBRETABLA);
			while (rs.next()) {
				reg = new Atomo();
				reg.setCodAtomo(rs.getLong(0));
				reg.setAtomo(rs.getString(1));
				reg.setDescripcion(rs.getString(2));
				lista.add(reg);
			}
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return lista;
	}

	public IEntidad getByLongId(long id) {
		Statement st;
		ResultSet rs;
		Atomo reg = new Atomo();
		try {
			st = conexion.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM " + NOMBRETABLA
					+ " WHERE cod_atomo = '" + id + "';");
			while (rs.next()) {
				reg.setCodAtomo(rs.getLong(0));
				reg.setAtomo(rs.getString(1));
				reg.setDescripcion(rs.getString(2));
			}
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return (IEntidad) reg;
	}

	public IEntidad getByStringId(String id) {
		Statement st;
		ResultSet rs;
		Atomo reg = new Atomo();
		try {
			st = conexion.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM " + NOMBRETABLA
					+ " WHERE atomo = '" + id + "';");
			while (rs.next()) {
				reg.setCodLista(rs.getLong(0));
				reg.setSublista(rs.getInt(1));
				reg.setOrden(rs.getInt(2));
				reg.setAtomo(rs.getString(3));
				reg.setEnlace(rs.getBoolean(4));
			}
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return (IEntidad) reg;
	}

	public ArrayList getFind(IEntidad desde, IEntidad hasta) {
		Statement st;
		ResultSet rs;
		ArrayList lista = new ArrayList();
		Atomo reg;

		try {
			st = conexion.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM " + NOMBRETABLA);
			while (rs.next()) {
				reg = new Atomo();
				reg.setCodLista(rs.getLong(0));
				reg.setSublista(rs.getInt(1));
				reg.setOrden(rs.getInt(2));
				reg.setAtomo(rs.getString(3));
				reg.setEnlace(rs.getBoolean(4));
				lista.add(reg);
			}
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return lista;
	}

	public boolean open() {
		return conexion.open();
	}

	/**
	 * Establece la conexión adecuada para el AlmacenAtomos
	 * 
	 * @param pConexion
	 */
	public void setConexion(Conexion pConexion) {
		conexion = pConexion;
	}
}
