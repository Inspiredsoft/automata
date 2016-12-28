package it.inspired.fsm;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a transition in a finite state machine is a connection from a starting state to a new state.
 * 
 * @author Massimo Romano
 *
 */
public class Transition {	
	/* Transition name */
	private String name;
	
	/* Transition description */
	private String description;

	/* Starting state of the transition */
	private State state;
	
	/* Arrival state name */
	private String to;
	
	/* Script executed if a transition is fired */
	private List<Script> scripts = new ArrayList<Script>();

	//---------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

	public void addScript( Script script ) {
		script.setTransition( this );
		scripts.add( script );
	}

	public List<Script> getScripts() {
		return this.scripts;
	}
	
	//---------------------------------------------------------------------------
	
	public String toString() {
		return this.description + " (" + this.name + ": " + this.state.getName() + " -> " + this.to + ")";
	}
}
