package Contrib;

//  Browser.java
//

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.html.*;
import javax.swing.event.*;

public class Browser extends JFrame {

    static JTextField textField;
    static JEditorPane editor;

    public Browser(String s) {
        super(s);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        editor = new JEditorPane();
        textField  = new JTextField();
        JScrollPane scrollPane = new JScrollPane(editor);

        editor.setEditable(false);

        panel.add(new JLabel("Location:  "), BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        editor.addHyperlinkListener(new HyperListener());
        textField.addActionListener(new TextFieldListener());
    }

    public static void main(String[] args) {
        Browser frame = new Browser("Browser");
        frame.setSize(400,400);
        frame.setVisible(true);
    }

    class HyperListener implements HyperlinkListener {

        public void hyperlinkUpdate(HyperlinkEvent e) {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                JEditorPane sourcePane = (JEditorPane) e.getSource();
                if (e instanceof HTMLFrameHyperlinkEvent) {
                    HTMLFrameHyperlinkEvent event=(HTMLFrameHyperlinkEvent)e;
                    HTMLDocument doc=(HTMLDocument)sourcePane.getDocument();
                    doc.processHTMLFrameHyperlinkEvent(event);
                } else {
                    try {
                        textField.setText("Contacting " +
                            e.getURL().toString() + " ...");
                        sourcePane.setPage(e.getURL()); 
                        textField.setText(e.getURL().toString());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    class TextFieldListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                editor.setPage(textField.getText());
            } catch (IOException ex) {
                editor.setText("Page could not be loaded");
            }
        }
    }
}
