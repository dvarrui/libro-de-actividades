/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.ui;

import java.net.*;
import java.io.*;
import terminal.shell.*;

/**
 *
 * @author david
 */
public class UIServidorRemoto implements InterfazUI {

    public static final int PUERTO = 2323;
    private InterfazShell consola;
    private ServerSocket servidor;
    private Socket cliente;
    private boolean fin;
    private BufferedReader in;
    private PrintWriter out;
    private int puerto;

    public UIServidorRemoto() {
        puerto = UIServidorRemoto.PUERTO;
    }
    
    public UIServidorRemoto(int puerto) {
        this.puerto = puerto;
    }
    
    public void iniciar(InterfazShell con) {
        consola = con;
        fin = false;
        this.esperarClienteRemoto();
        this.procesarMensajes();
    }

    private void procesarMensajes() {
        try {
            String ent = "";
            String sal = "";

            in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out = new PrintWriter(cliente.getOutputStream());

            sal = consola.getSalida();
            sal = sal.replaceAll("\n", "EOL");
            out.print(sal+"\n"); out.flush();

            while (!fin) {
                ent = in.readLine();
                 consola.setEntrada(ent);
                 
                if (ent.equals("exit")) {
                    fin = true;
                }
                sal = consola.getSalida();
                sal = sal.replaceAll("\n", "EOL");
                out.print(sal+"\n"); out.flush();
            }
            cliente.close();
        } catch (java.io.IOException e) {
            System.err.println("Error:" + e);
        }
    }

    private void esperarClienteRemoto() {
        try {
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();
            System.out.println(" Conexión aceptada desde " + cliente.getInetAddress());
        } catch (IOException e) {
            System.err.println(" [ERROR] " + e);
        }
    }
}
