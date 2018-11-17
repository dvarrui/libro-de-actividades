package cajas.ficheros;

import java.io.*;

public class Directorio {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Directorio d = new Directorio("c:/temp");
		Directorio d = new Directorio("/home/david");
		d.ls();
	}

	File dir;

	boolean verOcultos = false;

	public Directorio(String directorio) {
		File f = new File(directorio);
		if (f.isDirectory())
			dir = f;
		else
			dir = File.listRoots()[0];
	}

	public void cd(String directorio) {
		dir = new File(directorio);
	}

	public boolean isVerOcultos() {
		return verOcultos;
	}

	public void ls() {
		// Mostrar contenido del directorio
		// permisos, propietario, tama�o, fecha, nombre
		StringBuffer permisos;
		File[] archivos = dir.listFiles();

		System.out.println(dir.getAbsolutePath() + "/");

		for (int i = 0; i < archivos.length; i++) {
			permisos = new StringBuffer(30);
			if (!archivos[i].isHidden() || verOcultos) {
				if (archivos[i].canRead())
					permisos.append("r");
				else
					permisos.append("-");
				if (archivos[i].canWrite())
					permisos.append("w");
				else
					permisos.append("-");

				permisos.append("   " + archivos[i].length());
				while (permisos.length() < 13)
					permisos.append(" ");
				permisos.append("   " + archivos[i].lastModified());
				while (permisos.length() < 23)
					permisos.append(" ");

				System.out.print(permisos + "   " + archivos[i].getName());
				if (archivos[i].isDirectory())
					System.out.println("/");
				else
					System.out.println("");
			}
		}
		System.out.println("total " + archivos.length);
	}

	public String pwd() {
		return dir.getAbsolutePath();
	}

	public void setVerOcultos(boolean verOcultos) {
		this.verOcultos = verOcultos;
	}
}
