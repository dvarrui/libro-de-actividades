package coreservlets.WebClient;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** A graphical client that lets you interactively connect to
 *  Web servers and send custom request lines and
 *  request headers.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */


public class WebClient extends CloseableFrame
    implements Runnable, Interruptible, ActionListener {
	static final long serialVersionUID=1L;
  public static void main(String[] args) {
    new WebClient("Web Client");
  }

  private LabeledTextField hostField, portField,
          requestLineField;
  private TextArea requestHeadersArea, resultArea;
  private String host, requestLine;
  private int port;
  private String[] requestHeaders = new String[30];
  private Button submitButton, interruptButton;
  private boolean isInterrupted = false;

  public WebClient(String title) {
    super(title);
    setBackground(Color.lightGray);
    setLayout(new BorderLayout(5, 30));
    int fontSize = 14;
    Font labelFont =
      new Font("Serif", Font.BOLD, fontSize);
    Font headingFont =
      new Font("SansSerif", Font.BOLD, fontSize+4);
    Font textFont =
      new Font("Monospaced", Font.BOLD, fontSize-2);
    Panel inputPanel = new Panel();
    inputPanel.setLayout(new BorderLayout());
    Panel labelPanel = new Panel();
    labelPanel.setLayout(new GridLayout(4,1));
    hostField = new LabeledTextField("Host:", labelFont,
                                     30, textFont);
    portField = new LabeledTextField("Port:", labelFont,
                                     "80", 5, textFont);
    // Use HTTP 1.0 for compatibility with the most servers.
    // If you switch this to 1.1, you *must* supply a
    // Host: request header.
    requestLineField =
      new LabeledTextField("Request Line:", labelFont,
                           "GET / HTTP/1.0", 50, textFont);
    labelPanel.add(hostField);
    labelPanel.add(portField);
    labelPanel.add(requestLineField);
    Label requestHeadersLabel =
      new Label("Request Headers:");
    requestHeadersLabel.setFont(labelFont);
    labelPanel.add(requestHeadersLabel);
    inputPanel.add(labelPanel, BorderLayout.NORTH);
    requestHeadersArea = new TextArea(5, 80);
    requestHeadersArea.setFont(textFont);
    inputPanel.add(requestHeadersArea, BorderLayout.CENTER);
    Panel buttonPanel = new Panel();
    submitButton = new Button("Submit Request");
    submitButton.addActionListener(this);
    submitButton.setFont(labelFont);
    buttonPanel.add(submitButton);
    inputPanel.add(buttonPanel, BorderLayout.SOUTH);
    add(inputPanel, BorderLayout.NORTH);
    Panel resultPanel = new Panel();
    resultPanel.setLayout(new BorderLayout());
    Label resultLabel =
      new Label("Results", Label.CENTER);
    resultLabel.setFont(headingFont);
    resultPanel.add(resultLabel, BorderLayout.NORTH);
    resultArea = new TextArea();
    resultArea.setFont(textFont);
    resultPanel.add(resultArea, BorderLayout.CENTER);
    Panel interruptPanel = new Panel();
    interruptButton = new Button("Interrupt Download");
    interruptButton.addActionListener(this);
    interruptButton.setFont(labelFont);
    interruptPanel.add(interruptButton);
    resultPanel.add(interruptPanel, BorderLayout.SOUTH);
    add(resultPanel, BorderLayout.CENTER);
    setSize(600, 700);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == submitButton) {
      Thread downloader = new Thread(this);
      downloader.start();
    } else if (event.getSource() == interruptButton) {
      isInterrupted = true;
    } 
  }

  public void run() {
    isInterrupted = false;
    if (hasLegalArgs()) 
      new HttpClient(host, port, requestLine,
		     requestHeaders, resultArea, this);
  }
                         
  public boolean isInterrupted() {
    return(isInterrupted);
  }

  private boolean hasLegalArgs() {
    host = hostField.getTextField().getText();
    if (host.length() == 0) {
      report("Missing hostname");
      return(false);
    }
    String portString =
      portField.getTextField().getText();
    if (portString.length() == 0) {
      report("Missing port number");
      return(false);
    }
    try {
      port = Integer.parseInt(portString);
    } catch(NumberFormatException nfe) {
      report("Illegal port number: " + portString);
      return(false);
    }
    requestLine =
      requestLineField.getTextField().getText();
    if (requestLine.length() == 0) {
      report("Missing request line");
      return(false);
    }
    getRequestHeaders();
    return(true);
  }

  private void report(String s) {
    resultArea.setText(s);
  }

  private void getRequestHeaders() {
    for(int i=0; i<requestHeaders.length; i++)
      requestHeaders[i] = null;
    int headerNum = 0;
    String header =
      requestHeadersArea.getText();
    StringTokenizer tok =
      new StringTokenizer(header, "\r\n");
    while (tok.hasMoreTokens())
      requestHeaders[headerNum++] = tok.nextToken();
  }
}
