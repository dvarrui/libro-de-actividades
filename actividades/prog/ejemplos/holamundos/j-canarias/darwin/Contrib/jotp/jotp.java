package Contrib.jotp;

/* applet to serve as an s/key calculator application wrapper 
 * around otp class.
 *
 * Copyright 1996, Harry Mantakos, harry@cs.umd.edu
 */

import java.awt.*;
import java.util.StringTokenizer;

public class jotp extends java.applet.Applet {
    TextField otptf, chaltf, pwtf;
    final String md4label = "compute with MD4";
    final String md5label = "compute with MD5";
    final String version = "jotp 0.8";

    /* Just takes challenge info and passphrase info on the 
     * command line (ick) and spits out the resulting otp "words".
     *
     * Mainly for testing.
     */
    public static void main(String[] argv) {
	String seed, passphrase;
	int seq;
	otp otpwd;
	int hashalg;
	String hashtype;

	if ((argv.length < 3) || (argv.length > 4)) {
	    System.err.println("usage: jotp sequence seed passphrase" + 
			       "[md4|md5]");
	    return;
	}
	seq = new Integer(argv[0]).intValue();
	seed = new String(argv[1]);
	passphrase = new String(argv[2]);
	if ((argv.length == 3) || argv[3].equals("4") ||
	    argv[3].equals("md4") || argv[3].equals("MD4")) {
	    hashtype = "md4";
	    hashalg = otp.MD4;
	} else if (argv[3].equals("5") || argv[3].equals("md5") ||
		   argv[3].equals("MD5")) {
	    hashtype = "md5";
	    hashalg = otp.MD5;
	} else {
	    System.err.println("usage: jotp sequence seed passphrase " +
			       "[4|md4|5|md5]");
	    return;
	}

	otpwd = new otp(seq, seed, passphrase, hashalg);
	System.out.println("Using " + hashtype + ". Thinking...");
	otpwd.calc();
	System.out.println(otpwd);
    }

    public void init() {

	setBackground(Color.white);
	setLayout(new GridLayout(6,1));

	Panel panel1 = new Panel();
	add (panel1);
	Font titlefont = new Font("TimesRoman", Font.BOLD, 14);
	panel1.setFont(titlefont);
	panel1.add(new Label(String.valueOf(version) + 
			     ": The Java OTP (aka S/Key) calculator!"));
	Panel panel2 = new Panel();
	panel2.setLayout(new FlowLayout());
	add (panel2);
	panel2.add(new Label("Challenge (e.g. \"55 latour1\"):"));
        chaltf = new TextField(24);
	panel2.add(chaltf);

	Panel panel3 = new Panel();
	panel3.setLayout(new FlowLayout());
	add(panel3);
	panel3.add(new Label("Secret Password:"));
	pwtf = new TextField(24);
	pwtf.setEchoCharacter('*');
	panel3.add(pwtf);

	Panel panel4 = new Panel();
	panel4.setLayout(new FlowLayout());
	add(panel4);

	panel4.add (new Button(String.valueOf(md4label)));
	panel4.add (new Button(String.valueOf(md5label)));

	Panel panel6 = new Panel();
	panel6.setLayout(new FlowLayout());
	add(panel6);
	panel6.add(new Label("One-Time Password:", Label.LEFT));
	otptf = new TextField(40);
	panel6.add(otptf);

	Panel panel7 = new Panel();
	add(panel7);
	panel7.add(new Label("jotp by Harry Mantakos, " + 
			     "http://www.cs.umd.edu/~harry/jotp"));
    }
    public boolean action(Event evt, Object arg) {
	String tmpstr, tmpstr2, seed, passphrase;
	int seq, hashalg;
	otp otpwd;

	if (evt.target instanceof Button) {
	    if (arg.equals(md5label)) {
		hashalg = otp.MD5;
	    } else {
		hashalg = otp.MD4;
	    }

	    /* Split up challenge */
	    tmpstr = chaltf.getText();
	    StringTokenizer st = new StringTokenizer(tmpstr);
	    if (st.countTokens() != 2) {
		otptf.setText("bogus challenge");
		return true;
	    }
	    tmpstr2 = st.nextToken();
	    try {
		seq = (Integer.parseInt(tmpstr2));
	    } catch (NumberFormatException e) {
		otptf.setText("bogus sequence number '" + tmpstr2 + "'");
		return true;
	    }
	    seed = st.nextToken();
	    passphrase = pwtf.getText();
/*	    passphrase = "eat me";*/
            System.out.println("passphrase = " + passphrase);
	    otptf.setText("Okay, thinking...");
	    otpwd = new otp(seq, seed, passphrase, hashalg);
	    otpwd.calc();
	    otptf.setText(otpwd.toString());
	}
	return true;
    }
}
