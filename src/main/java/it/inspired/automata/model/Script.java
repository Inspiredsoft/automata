package it.inspired.automata.model;

/**
 * A script can be an action or a condition in a transition
 * 
 * @author Massimo Romano
 *
 */
public class Script {
	
	/* Groovy script */
	private groovy.lang.Script code;
	
	/* Transition */
	private Transition transition;

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public groovy.lang.Script getCode() {
		return code;
	}

	public void setCode(groovy.lang.Script groovyScript) {
		this.code = groovyScript;
	}
	
}
