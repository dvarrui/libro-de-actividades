package Contrib;

import java.net.*;
import java.io.*;

/** An "nslookup" clone in Java.
 * @author Elliot Rusty Harold, O'Reilly & Associates
 */
public class JavaLookup {

   public static void main (String args[]) {

   if (args.length > 0) { // use command line
     for (int i = 0; i < args.length; i++) {
        lookup(args[i]);
     }
   }
   else {
     DataInputStream myInputStream = new DataInputStream(System.in);
     System.out.println
       ("Enter names and IP addresses. Enter \"exit\" to quit.");
     while (true) {
       String s;
       try {
         s = myInputStream.readLine();
       }
       catch (IOException e) {
         break;
       }
       if (s.equals("exit")) break;
       if (s.equals("quit")) break;
	   if (s.charAt(0) == '\004') break;	// unix ^D
       lookup(s);
     }

   }

  } /* end main */


  private static void lookup(String s) {

    InetAddress thisComputer;
    byte[] address;

    // get the bytes of the IP address
    try {
      thisComputer = InetAddress.getByName(s);
      address = thisComputer.getAddress();
    }
    catch (UnknownHostException ue) {
       System.out.println("Cannot find host " + s);
       return;
    }

    if (isHostname(s)) {
      // Print the IP address
      for (int i = 0; i < address.length; i++) {
        int unsignedByte = address[i] < 0 ? address[i] + 256 : address[i];
        System.out.print(unsignedByte + ".");
      }
      System.out.println();
    }
    else {  // this is an IP address
      try {
        System.out.println(InetAddress.getByName(s));
      }
      catch (UnknownHostException e) {
        System.out.println("Could not lookup the address " + s);
      }
    }

  }  // end lookup

   private static boolean isHostname(String s) {

     char[] ca = s.toCharArray();
     // if we see a character that is neither a digit nor a period
     // then s is probably a hostname
     for (int i = 0; i < ca.length; i++) {
       if (!Character.isDigit(ca[i])) {
         if (ca[i] != '.') {
           return true;
         }
       }
     }

     // Everything was either a digit or a period
     // so s looks like an IP address in dotted quad format
     return false;

   }  // end isHostName

 } // end javalookup
