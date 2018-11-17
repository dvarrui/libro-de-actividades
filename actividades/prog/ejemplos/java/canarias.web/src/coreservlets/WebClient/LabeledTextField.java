package coreservlets.WebClient;

import java.awt.*;

/** A TextField with an associated Label.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class LabeledTextField extends Panel {
	static final long serialVersionUID=1L;
  private Label label;
  private TextField textField;
  
  public LabeledTextField(String labelString,
                          Font labelFont,
                          int textFieldSize,
                          Font textFont) {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    label = new Label(labelString, Label.RIGHT);
    if (labelFont != null)
      label.setFont(labelFont);
    add(label);
    textField = new TextField(textFieldSize);
    if (textFont != null)
      textField.setFont(textFont);
    add(textField);
  }

  public LabeledTextField(String labelString,
                          String textFieldString) {
    this(labelString, null, textFieldString,
         textFieldString.length(), null);
  }

  public LabeledTextField(String labelString,
                          int textFieldSize) {
    this(labelString, null, textFieldSize, null);
  }
  
  public LabeledTextField(String labelString,
                          Font labelFont,
                          String textFieldString,
                          int textFieldSize,
                          Font textFont) {
    this(labelString, labelFont,
         textFieldSize, textFont);
    textField.setText(textFieldString);
  }

  /** The Label at the left side of the LabeledTextField.
   *  To manipulate the Label, do:
   *  <PRE>
   *    LabeledTextField ltf = new LabeledTextField(...);
   *    ltf.getLabel().someLabelMethod(...);
   *  </PRE>
   *
   * @see #getTextField
   */
  
  public Label getLabel() {
    return(label);
  }

  /** The TextField at the right side of the
   *  LabeledTextField.
   *
   * @see #getLabel
   */
  
  public TextField getTextField() {
    return(textField);
  }
}
