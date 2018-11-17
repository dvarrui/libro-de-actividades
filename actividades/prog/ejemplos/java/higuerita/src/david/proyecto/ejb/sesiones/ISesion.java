package david.proyecto.ejb.sesiones;

import java.util.Date;

public interface ISesion {

	public String getEntorno();

	public Date getFechaLogin();

	public boolean isLogin();

	public boolean login(String usuario, String clave);

	public boolean logout();

	public boolean tienePermiso(String permiso);
}
