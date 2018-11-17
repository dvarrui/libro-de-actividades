/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.ui;

import terminal.util.ConsolaIO;
import terminal.shell.InterfazShell;

/**
 *
 * @author david
 */
public class UITextoPlano implements InterfazUI {

    public void iniciar(InterfazShell con) {

        ConsolaIO.println("\nEntrando en consola virtual...");
        ConsolaIO.printlnSimbolo('<', 35);

        ConsolaIO.print(con.getSalida());
        
        while (!con.isFin()) {
            con.setEntrada(ConsolaIO.readLine());
            ConsolaIO.print(con.getSalida());
        }
        ConsolaIO.printlnSimbolo('>', 35);
        ConsolaIO.println("Consola virtual cerrada.\n");
    }
}
