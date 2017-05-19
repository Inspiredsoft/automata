package it.inspired.automata.exception;

public class NoTransitionAllowed extends Exception {

	private static final long serialVersionUID = 3150189417478297082L;

	public NoTransitionAllowed(String msg) {
		super(msg);
	}
}
