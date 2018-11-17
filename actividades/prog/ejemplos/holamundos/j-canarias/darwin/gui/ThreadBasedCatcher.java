package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ThreadBasedCatcher - Demonstrate catching uncaught exceptions 
 * thrown in an unrelated Thread.
 * @author ian
 * @verion $Id: ThreadBasedCatcher.java,v 1.3 2004/09/08 20:12:55 ian Exp $
 */
public class ThreadBasedCatcher extends JFrame{

	public static void main(String[] args) {
		new ThreadBasedCatcher().setVisible(true);
	}
	public ThreadBasedCatcher(){
		Container cp = getContentPane();
		JButton crasher = new JButton("Crash");
		cp.add(crasher);
		crasher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				throw new RuntimeException("You asked for it");
			}
		});
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler(){
					public void uncaughtException(Thread t, Throwable ex){
						System.out.println(
							"You crashed thread " + t.getName());
						System.out.println(
							"Exception was: " + ex.toString());
					}
				});
		pack();
	}
}
