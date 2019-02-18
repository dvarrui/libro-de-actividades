package com.darwinsys.regex;

/** 
 * Tiny class for passing integer in AND getting its updated value back.
 * Like java.lang.Integer, but smaller and much more specialized.
 */
public class Int {
	int val;
	/** Construct an Int with a given starting value */
	public Int(int i) { val = i; }
	/** Construct an Int */
	public Int() { val = 0; }
	/** Set the value of an Int */
	public void set(int i) { val = i; }
	/** Get the value of an Int */
	public int  get() { return val; }
	/** Increment the value of an Int by unity. */
	public void incr() { val++; }
	/** Increment the value of an Int by a given value */
	public void incr(int i) { val += i;}
	/** Decrement the value of an Int by unity. */
	public void decr() { val++; }
	/** Decrement the value of an Int by a given value */
	public void decr(int i) { val -= i;}
	/** Return this Int as a String. */
	public String toString() { return Integer.toString(val); }
}
