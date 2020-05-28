/*
 * Autor: David Vargas <dvargas@canarias.org>
 * Versión: 0.2.0
 */
package terminal.shell;

import java.io.File;
import java.util.HashMap;
import java.util.Date;

/**
 * Esta clase implementa la misma funcionalidad que una consola<br>
 * o interfaz de modo comando.<br>
 * @author david
 */
public class ShellAPIjava implements InterfazShell {

    public static final String RUTA_INICIAL = "/home/david/tmp";
    private boolean fin;
    private String salida;
    private String rutaActual;
    private HashMap<String, String> parametros;

    /**
     * Constructor de la clase ShellAPIjava
     */
    public ShellAPIjava() {
        this.inicializar();
    }

    public ShellAPIjava(String ruta_inicial) {
        this.inicializar();
        rutaActual = ruta_inicial;
    }

    public boolean isFin() {
        return this.fin;
    }

    public void setEntrada(String entrada) {
        this.mapearEntrada(entrada);

        if (parametros.get("comando").equals("exit")) {
            fin = true;
            salida = "Saliendo de la consola virtual...\n";
        } else if (parametros.get("comando").equals("help")) {
            salida = this.ejecutaRhelp();
        } else if (parametros.get("comando").equals("cd")) {
            salida = this.ejecutaRcd();
        } else if (parametros.get("comando").equals("dir")) {
            this.mapearEntrada(entrada + " -c");
            salida = this.ejecutaRls();
        } else if (parametros.get("comando").equals("ls")) {
            salida = this.ejecutaRls();
        } else if (parametros.get("comando").equals("mkdir")) {
            salida = this.ejecutaRmkdir();
        } else if (parametros.get("comando").equals("pwd")) {
            salida = this.ejecutaRpwd();
        } else if (parametros.get("comando").equals("rmdir")) {
            salida = this.ejecutaRrmdir();
        } else if (parametros.get("comando").equals("vdir")) {
            this.mapearEntrada(entrada + " -l -c");
            salida = this.ejecutaRls();
        } else {
            salida = "No se entiende la orden: " + entrada;
        }
        salida = salida + "\n" + this.getPrompt();
    }

    public String getSalida() {
        if (fin) {
            salida = "";
        }
        return salida;
    }

    public String getPrompt() {
        return "consola[" + rutaActual + "]$ ";
    }

    //Métodos privados
    private void inicializar() {
        fin = false;
        rutaActual = RUTA_INICIAL;
        salida = "Consola Virtual 0.1.3\n" + "Fecha: 2008-01-15\n" + "Autor: David Vargas\n";
        salida = salida + "Sistema Operativo: " + System.getProperty("os.name") + "\n";
        salida = salida + this.getPrompt();
        parametros = new HashMap<String, String>();
    }

    private void mapearEntrada(String entrada) {
        String p[] = entrada.trim().split(" ");
        parametros.clear();
        parametros.put("comando", p[0]);
        for (int i = 1; i < p.length; i++) {
            parametros.put("parametro_" + i, p[i]);
        }
    }

    private String ejecutaRhelp() {
        String s = "";
        s = s + "Ayuda de la ConsolaVirtual:\n";
        s = s + " * help\t, muestra esta ayuda.\n";
        s = s + " * exit\t, salir de la consola.\n";
        s = s + "\n";
        s = s + " * cd\t, cambia el directorio actual.\n";
        s = s + " * ls\t, muestra el contenido del directorio.\n";
        s = s + " * mkdir\t, crear un nuevo directorio.\n";
        s = s + " * mv\t, renombrar archivo.\n";
        s = s + " * pwd\t, muestra el directorio actual.\n";

        return s;
    }

    private String ejecutaRcd() {
        File dir;
        salida = "";
        try {
            if (parametros.size() == 1) {
                //Respuesta al comando 'cd'
                rutaActual = RUTA_INICIAL;
            } else {
                dir = new File(rutaActual + "/" + parametros.get("parametro_1"));
                dir = dir.getCanonicalFile();
                if (dir.exists() && dir.isDirectory()) {
                    rutaActual = dir.getCanonicalPath();
                } else {
                    salida = "Directorio " + parametros.get("parametro_1") + " incorrecto.";
                }

            }
        } catch (java.io.IOException e) {
            salida = e.toString();
        }
        return salida;
    }

    private String ejecutaRls() {
        String s = "";
        int total = 0;
        try {
            File dir = new File(rutaActual);

            File[] ficheros = dir.listFiles();
            for (int i = 0; i < dir.listFiles().length; i++) {
                if (!parametros.containsValue("-a") && ficheros[i].isHidden()) {
                    continue;
                }
                if (parametros.containsValue("-d") && !ficheros[i].isDirectory()) {
                    continue;
                }

                if (parametros.containsValue("-l")) {
                    s = s + this.getAtributosArchivo(ficheros[i]) + this.getNombreArchivo(ficheros[i]) + "\n";
                } else {
                    s = s + this.getNombreArchivo(ficheros[i]) + "\t";
                }
                total++;
            }
            s = s + "\nTotal de archivos " + total + "\n";
        } catch (Exception e) {
            System.err.println(e);
        }
        return s;
    }

    private String ejecutaRmkdir() {
        String s = "";
        String ruta;
        try {
            if (parametros.size() != 2) {
                s = "Número de argumentos incorrecto.";
            } else {
                ruta = this.getRutaAbsoluta(parametros.get("parametro_1"));
                File f = new File(ruta);
                if (f.exists()) {
                    s = "Existe el archivo " + ruta;
                } else {
                    if (f.mkdir()) {
                        s = "Directorio creado.";
                    } else {
                        s = "No se ha podido crear el directorio.\n";
                        s += "Revise los permisos.";
                    }
                }
            }
        } catch (Exception e) {
            s = "[mkdir] " + e;
        }
        return s;
    }

    private String ejecutaRpwd() {
        return this.rutaActual;
    }

    private String ejecutaRrmdir() {
        String s = "";
        String ruta;
        try {
            if (parametros.size() != 2) {
                s = "Número de argumentos incorrecto.";
            } else {
                ruta = this.getRutaAbsoluta(parametros.get("parametro_1"));
                File f = new File(ruta);
                if (!f.exists()||!f.isDirectory()) {
                    s = "No existe el fichero " + ruta+" o no es un directorio.";
                } else {
                    if (f.delete()) {
                        s = "Directorio eliminado.";
                    } else {
                        s = "No se ha podido borar el directorio.\n";
                        s += "Revise los permisos o su contenido.";
                    }
                }
            }
        } catch (Exception e) {
            s = "[mkdir] " + e;
        }
        return s;
    }

    private String getNombreArchivo(File fichero) {
        String s = fichero.getName();
        if (parametros.containsValue("-c")) {
            s += (fichero.isDirectory() ? "/" : "");
            s += (fichero.isFile() && fichero.canExecute() ? "*" : "");
        }
        return s;
    }

    private String getAtributosArchivo(File fichero) {
        String s = "";
        Date f = new Date(fichero.lastModified());

        s += f.toString();
        s += "\t" + fichero.length() + "\t";

        s += (fichero.isDirectory() ? "d" : "-");
        s += (fichero.isHidden() ? "h" : "-");
        s += " ";
        s += (fichero.canRead() ? "r" : "-");
        s += (fichero.canWrite() ? "w" : "-");
        s += (fichero.canExecute() ? "x" : "-");

        s += " \t";
        return s;
    }

    private String getRutaAbsoluta(String fichero) {
        if (!fichero.startsWith("/") && !fichero.startsWith("c:\\")) {
            //Tenemos una ruta relativa
            fichero = this.rutaActual + "/" + fichero;
        }
        return fichero;
    }
}
