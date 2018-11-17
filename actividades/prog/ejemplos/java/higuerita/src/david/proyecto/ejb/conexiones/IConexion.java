package david.proyecto.ejb.conexiones;

import david.proyecto.ejb.sesiones.ISesion;

public interface IConexion {

	public boolean desconectar();
	public ISesion getSesion();
	public boolean isConectado();
}
