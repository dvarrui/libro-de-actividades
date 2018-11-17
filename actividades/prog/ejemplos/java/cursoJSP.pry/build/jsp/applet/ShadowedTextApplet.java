package applet;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Applet interface to the ShadowedTextFrame
 *  class. Requires Swing and Java 2D.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ShadowedTextApplet extends JApplet
                                implements ActionListener {
  private JTextField messageField;
  private JComboBox fontBox;
  private JSlider fontSizeSlider;
  private JButton showFrameButton;
  
  public void init() {
    WindowUtilities.setNativeLookAndFeel();
    Color bgColor = new Color(0xFD, 0xF5, 0xE6);
    Font font = new Font("Serif", Font.PLAIN, 16);
    Container contentPane = getContentPane();
    contentPane.setLayout(new GridLayout(4, 1));
    contentPane.setBackground(bgColor);

    // Use a JTextField to gather the text for the message.
    // If the MESSAGE parameter is in the HTML,
    // use it as the initial value of this text field.
    messageField = new JTextField(20);
    String message = getParameter("MESSAGE");
    if (message != null) {
      messageField.setText(message);
    }
    JPanel messagePanel =
      new LabelPanel("Message:", "Message to Display",
                     bgColor, font, messageField);
    contentPane.add(messagePanel);

    // Use a JComboBox to let users choose any
    // font available on their system.
    GraphicsEnvironment env =
      GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] fontNames = env.getAvailableFontFamilyNames();
    fontBox = new JComboBox(fontNames);
    fontBox.setEditable(false);
    JPanel fontPanel =
      new LabelPanel("Font:", "Font to Use",
                     bgColor, font, fontBox);
    contentPane.add(fontPanel);

    // Use a JSlider to select the font size.
    fontSizeSlider = new JSlider(0, 150);
    fontSizeSlider.setBackground(bgColor);
    fontSizeSlider.setMajorTickSpacing(50);
    fontSizeSlider.setMinorTickSpacing(25);
    fontSizeSlider.setPaintTicks(true);
    fontSizeSlider.setPaintLabels(true);
    JPanel fontSizePanel =
      new LabelPanel("Font Size:", "Font Size to Use",
                     bgColor, font, fontSizeSlider);
    contentPane.add(fontSizePanel);

    // Pressing the button will open the frame
    // that shows the shadowed text.
    showFrameButton = new JButton("Open Frame");
    showFrameButton.addActionListener(this);
    JPanel buttonPanel =
      new LabelPanel("Show Shadowed Text:",
                     "Open JFrame to Show Shadowed Text",
                     bgColor, font, showFrameButton);
    contentPane.add(buttonPanel);
  }

  public void actionPerformed(ActionEvent event) {
    String message = messageField.getText();
    if (message.length() == 0) {
      message = "No Message";
    }
    String fontName = (String)fontBox.getSelectedItem();
    int fontSize = fontSizeSlider.getValue();
    JFrame frame = new JFrame("Shadowed Text");
    JPanel panel =
      new ShadowedTextFrame(message, fontName, fontSize);
    frame.setContentPane(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
