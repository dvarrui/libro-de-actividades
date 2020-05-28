/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.shell;

import java.net.*;
import java.io.*;

/**
 * Esta clase sirve para probar la conexión a un {host:puerto} determinado.
 * De esta forma se puede comprobar si los servicios están activos.
 * 
 * @author David Vargas Ruiz
 *
 */
public class ShellClienteRemoto implements InterfazShell {
    public static final int PUERTO=2323;
    private boolean fin = false;
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;

    public ShellClienteRemoto() {
        this.inicializar("127.0.0.1", ShellClienteRemoto.PUERTO);
    }

    public ShellClienteRemoto(String host) {
        this.inicializar(host, ShellClienteRemoto.PUERTO);
    }

    public ShellClienteRemoto(String host, int puerto) {
        this.inicializar(host, puerto);
    }
    
    private void inicializar(String host, int puerto) {
        this.fin = false;
        try {
            this.sock = new Socket(host, puerto);

            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream());

            System.out.println("\nConexión  [" + host + ":" + puerto + "] establecida");
        } catch (java.io.IOException e) {
            System.err.println(" Error conectando con [" + host + ":" + puerto + "] : " + e);
        }
    }

    public void setEntrada(String comando) {
        try {
            out.print(comando + "\n");out.flush();

            if (comando.equals("exit")) {
                this.fin = true;
                sock.close();
            }
        } catch (java.io.IOException e) {
            System.err.println(" Error enviando datos: " + e);
            System.err.println(" Comando: " + comando);
        }
    }

    public String getSalida() {
        String s = "";
        try {
            s=in.readLine();
            s= s.replaceAll("EOL", "\n");
        } catch (Exception e) {
            System.err.println(" Error recibiendo datos: " + e);
        } 
        return s;
    }

    public boolean isFin() {
        return this.fin;
    }
    
     public String getPrompt() {
        return "Cliente remoto";
    }
}
