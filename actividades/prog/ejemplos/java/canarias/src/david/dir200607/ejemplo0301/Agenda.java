package david.dir200607.ejemplo0301;

import java.util.ArrayList;

public class Agenda {
	private ArrayList contactos;

	Agenda() {
		contactos = new ArrayList();
	}

	public int getNumeroContactos() {
		return contactos.size();
	}

	public void agregarContacto(Persona p) {
		this.contactos.add(p);
	}

	public Persona getPersona(int index) {
		return (Persona) this.contactos.get(index);
	}

	public void eliminarContacto(Persona pEliminar) {
		Persona pCursor;
		for (int i = 0; i < this.contactos.size(); i++) {
			pCursor = (Persona) this.contactos.get(i);
			if (pCursor.equals(pEliminar)) {
				this.contactos.remove(i);
			}
		}
	}
}
