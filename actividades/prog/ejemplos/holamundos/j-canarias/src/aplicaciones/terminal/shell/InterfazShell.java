
package terminal.shell;

/**
 *
 * @author david
 */
public interface InterfazShell {

    public void setEntrada(String comando);
    public String getSalida();
    public boolean isFin();
    public String getPrompt();
}
