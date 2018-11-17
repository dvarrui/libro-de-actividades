package terminal.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

/**
 * <p>Esta clase implementa la misma funcionalidad que una consola
 * o interfaz de modo comando.</p>
 * <p>Actúa de intermediario entre elusuario y la auténtica shell del sistema.
 * Intercepta los mensajes entre ambos niveles.</p>
 * 
 * @author David Vargas <dvargas@canarias.org>
 */
public class ShellExec implements InterfazShell {

    private boolean fin;
    private String salida;
    private String prompt;
    private File pwd;
    private BufferedReader in;
    private Runtime runtime;

    /**
     * Constructor de la clase ShellAPIjava
     */
    public ShellExec() {
        fin = false;
        pwd = new File("/home/david/tmp");
        prompt = "[" + pwd + "]$ ";
        salida = "Consola ShellExec 0.1.0\n" + "Fecha: 2007-12-29\n" + "Autor: David Vargas\n";
        salida = salida + prompt;
        runtime = Runtime.getRuntime();
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
            String s = "", t = "";
            try {
                Process p = runtime.exec(cmd);
                p.waitFor(); // wait for process to complete
                in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((t = in.readLine()) != null) {
                    s = s + t + "\n";
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
            salida = s + "\n" + prompt;
        }
    }

    public String getSalida() {
        if (fin) {
            salida = "";
        }
        return salida;
    }
}
