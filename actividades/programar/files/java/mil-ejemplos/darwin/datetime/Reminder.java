package datetime;

import java.util.*;
/**
 * Beep every 5 minutes.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Reminder.java,v 1.4 2004/09/08 20:12:56 ian Exp $
 */
public class Reminder {
	public static void main(String[] argv) throws InterruptedException {
		//+
		while (true) {
			System.out.println(new Date() + "\007");
			Thread.sleep(5*60*1000);
		}
		//-
	}
}
