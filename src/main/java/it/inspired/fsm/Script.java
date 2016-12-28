package it.inspired.fsm;


public class Script {

	private groovy.lang.Script code;
	
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
