package david.tenerife.ejb.conexiones;

import david.tenerife.ejb.sesiones.ISesion;

public interface IConexion {

	public boolean desconectar();
	public ISesion getSesion();
	public boolean isConectado();
}
