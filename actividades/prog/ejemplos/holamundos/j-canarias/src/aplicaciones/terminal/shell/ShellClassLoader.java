package terminal.shell;

import java.util.HashMap;
import java.io.File;

/**
 * <p>Esta clase implementa la misma funcionalidad que una consola
 * o interfaz de modo comando.</p>
 * <p>Actúa de intermediario entre el usuario y el sistema operativo. 
 * Realiza llamadas a las APIs de java para interactuar con el entorno.</p>
 * <p>Los comandos están implementados en clases independientes que se cargan
 * y ejecutan bajo demanda.</p>
 * @author David Vargas <dvargas@canarias.org>
 */
public class ShellClassLoader implements InterfazShell {

    private boolean fin;
    private String salida;
    private String prompt;
    private File pwd;
    private HashMap<String, Object> comandos;

    /**
     * Constructor de la clase ShellAPIjava
     */
    public ShellClassLoader() {
        fin = false;
        pwd = new File("/home/david/tmp");
        prompt = "[" + pwd + "]$ ";
        salida = "Consola ShellClasLoader 0.1.0\n" + "Fecha: 2007-12-29\n" + "Autor: David Vargas\n";
        salida = salida + "Sistema Operativo: "+System.getProperty("os.name")+"\n";
        salida = salida + prompt;
    }

    public String getPrompt() {
        return prompt;
    }
    
    public boolean isFin() {
        return this.fin;
    }

    public void setEntrada(String cmd) {
        cmd = cmd.trim();

        if (cmd.equals("exit")) {
            //Salir de la consola
            this.fin = true;
            this.salida = "Saliendo de la consola\n";
        } else {
            String p[];
            p = cmd.split(" ");

            if (!comandos.containsKey(p[0])) {
                //Creamos una instancia del comando
                Class c = null;
                Object o = null;
                try {
                    // Load the class, return a Class for it
                    c = Class.forName("terminal.shell.comandos." + p[0]);
                    // Construct an object, as if new Type()
                    o = c.newInstance();
                    comandos.put(p[0], o);
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                }
            }
            //Este comando ya ha sido creado (instanciado)
            //ComandoShell c=(ComandoShell) comandos.getValue(p[0]);
            //salida = c.ejecutar(pwd);
            salida = salida + "\n" + prompt;
        }
    }

    public String getSalida() {
        if (fin) {
            salida = "";
        }
        return salida;
    }
}
