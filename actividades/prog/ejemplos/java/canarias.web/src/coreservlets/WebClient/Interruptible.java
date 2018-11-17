package coreservlets.WebClient;

/** An interface for classes that can be polled to see
 *  if they've been interrupted. Used by HttpClient
 *  and WebClient to allow the user to interrupt a network
 *  download.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public interface Interruptible {
  public boolean isInterrupted();
}
