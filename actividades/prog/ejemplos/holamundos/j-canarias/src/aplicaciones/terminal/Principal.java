/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import terminal.ui.*;
import terminal.shell.*;

/**
 *
 * @author David Vargas <dvargas@canarias.org>
 */
public class Principal {

    public static void main(String[] args) {
        InterfazUI ui = new UITextoPlano();
        InterfazShell con = new ShellAPIjava();

        if (args.length > 0) {
            if (args[0].equals("-local")) {
                ui = new UITextoPlano();
                con = new ShellAPIjava();
            } else if (args[0].equals("-exec")) {
                ui = new UITextoPlano();
                con = new ShellExec();
            } else if (args[0].equals("-cliente")) {
                ui = new UITextoPlano();
                if (args.length == 2) {
                    con = new ShellClienteRemoto(args[1]);
                } else if (args.length == 3) {
                    con = new ShellClienteRemoto(args[1], Integer.parseInt(args[2]));
                } else {
                    con = new ShellClienteRemoto();
                }
            } else if (args[0].equals("-servidor")) {
                ui = new UIServidorRemoto();
                if (args.length==2) {
                    //Se pasa como segundo argumento el puerto de escucha
                    ui = new UIServidorRemoto(Integer.parseInt(args[1]));
                }
                con = new ShellExec();
            } else {
                System.err.println("Error en los parámetros de entrada");
            }
        }
        //Iniciar el interfaz de usuario con la consola elegida
        ui.iniciar(con);
    }
}
