package Contrib.oreilly.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class IanDemo extends JFrame {
	public static void main(String[] args) {
        UIManager.put(JogShuttleUI.UI_CLASS_ID, "BasicJogShuttleUI");
		new IanDemo().setVisible(true);
	}

	JogShuttle myJog;

	IanDemo() {
		myJog = new JogShuttle(0, 300, 150);
        myJog.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName() == "value") {
					System.out.println(myJog.getValue());
				}
			}
		});
		getContentPane().add(myJog);
		pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
