package cajas.ficheros;

import cajas.ficheros.io.FileIO;

import java.io.*;

/**
 * ExecAndPrint runs a program using Runtime.exec(), read the program's output,
 * and returns its exit status.
 */
public class ExecAndPrint {

    /** Need a Runtime object for any of these methods */
    protected static Runtime r = Runtime.getRuntime();

    public static void main(String args[]) {
        int i;
        try {
            //ExecAndPrint.run("ls -lcF");
            System.err.println("Ejecutando...");
            i = ExecAndPrint.run("ping 192.168.0.1 -c 1 ");
            //System.in.read();
            System.err.println("Resultado = " + i);
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
    }

    /** Run the command given as a String, printing its output to System.out */
    public static int run(String cmd) throws IOException {
        return run(cmd, new OutputStreamWriter(System.out));
    }

    /** Run the command given as a String, print its output to "out" */
    public static int run(String cmd, Writer out) throws IOException {

        //String line;

        Process p = r.exec(cmd);

        FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, true);
        try {
            p.waitFor(); // wait for process to complete
        } catch (InterruptedException e) {
            return -1;
        }
        return p.exitValue();
    }

    /** Run the command given as a String[], print its output to System.out */
    public static int run(String[] cmd) throws IOException {
        return run(cmd, new OutputStreamWriter(System.out));
    }

    /** Run the command given as a String[], print its output to "out" */
    public static int run(String[] cmd, Writer out) throws IOException {

        //String line;

        Process p = r.exec(cmd);

        FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, true);

        try {
            p.waitFor(); // wait for process to complete
        } catch (InterruptedException e) {
            return -1;
        }
        return p.exitValue();
    }
}
