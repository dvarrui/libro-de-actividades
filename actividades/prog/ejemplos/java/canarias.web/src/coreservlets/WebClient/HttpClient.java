package coreservlets.WebClient;

import java.awt.*;
import java.net.*;
import java.io.*;

/** The underlying network client used by WebClient.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class HttpClient extends NetworkClient {
  private String requestLine;
  private String[] requestHeaders;
  private TextArea outputArea;
  private Interruptible app;
  
  public HttpClient(String host, int port,
                    String requestLine, String[] requestHeaders,
                    TextArea outputArea, Interruptible app) {
    super(host, port);
    this.requestLine = requestLine;
    this.requestHeaders = requestHeaders;
    this.outputArea = outputArea;
    this.app = app;
    if (checkHost(host))
      connect();
  }

  protected void handleConnection(Socket uriSocket)
      throws IOException {
    try {
      PrintWriter out = SocketUtil.getWriter(uriSocket);
      BufferedReader in = SocketUtil.getReader(uriSocket);
      outputArea.setText("");
      out.println(requestLine);
      for(int i=0; i<requestHeaders.length; i++) {
        if (requestHeaders[i] == null)
          break;
        else
          out.println(requestHeaders[i]);
      }
      out.println();
      String line;
      while ((line = in.readLine()) != null &&
             !app.isInterrupted())
        outputArea.append(line + "\n");
      if (app.isInterrupted())
        outputArea.append("---- Download Interrupted ----");
    } catch(Exception e) {
      outputArea.setText("Error: " + e);
    }
  }

  private boolean checkHost(String host) {
    try {
      InetAddress.getByName(host);
      return(true);
    } catch(UnknownHostException uhe) {
      outputArea.setText("Bogus host: " + host);
      return(false);
    }
  }
}
