package it.inspired.automata;

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
	
	private Script condition;
	
	/* Actions executed if a transition is fired */
	private List<Script> actions = new ArrayList<Script>();

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

	public void addAction( Script action ) {
		action.setTransition( this );
		actions.add( action );
	}

	public List<Script> getActions() {
		return this.actions;
	}
	
	public void setCondition( Script condition ) {
		condition.setTransition( this );
		this.condition = condition;
	}

	public Script getCondition() {
		return this.condition;
	}
	
	//---------------------------------------------------------------------------
	
	public String toString() {
		return this.description + " (" + this.name + ": " + this.state.getName() + " -> " + this.to + ")";
	}
}
