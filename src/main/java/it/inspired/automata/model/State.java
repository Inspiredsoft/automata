package it.inspired.automata.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent a state in a finite state machine workflow.
 * 
 * @author Massimo Romano
 *
 */
public class State {
	/* State name */
	private String name;
	
	/* State description */
	private String description;

	/* Workflow of the state */
	private Workflow workflow;
	
	/* Transition associated to the state */
	private Map<String,Transition> transitions = new HashMap<String,Transition>();
	
	//---------------------------------------------------------------------------
	
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Transition getTransition( String key ) {
		return (Transition)transitions.get( key );
	}
	public void addTransition( Transition transition ) {
		if( transitions.containsKey( transition.getName() ) ) {
			throw new RuntimeException( "Transition " + transition.getName() + " already present in the state " + this.name + " of the workflow " + this.workflow.getName() );
		} else {
			if( transition.getTo() == null ){
				transition.setTo( this.getName() );
			}
			transition.setState( this );
			transitions.put( transition.getName(), transition );
		}
	}
	
	public Collection<Transition> getTransitions() {
		return transitions.values();
	}
}
