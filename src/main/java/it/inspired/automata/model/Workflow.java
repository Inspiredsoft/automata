package it.inspired.automata.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A workflow defining a finite state machine.
 * 
 * @author Massimo Romano
 *
 */
public class Workflow {
	
	/* The name of the workflow */
	private String name;
	
	/* The starting state of the workflow item */
	private String start;
	
	/* The name of the item managed by the workflow */
	public String itemName;
	
	/* A map of allowed state */
	private Map<String,State> states = new HashMap<String,State>();

	//----------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	//----------------------------------------------------------------------------
	
	/**
	 * Return the state defined in the workflow by name
	 * @param name Name of the state
	 * @return The state defined in the workflow
	 */
	public State getState( String name ) {
		return (State)states.get( name );
	}
	
	/**
	 * Add a state in the workflow definition
	 * @param state
	 */
	public void addState( State state ) {
		if( states.containsKey( state.getName() ) ) {
			throw new RuntimeException( "The state " + state.getName() + " already defined int the workflow " + this.name );
		}
		state.setWorkflow( this );
		states.put( state.getName(), state );
	}
	
	/**
	 * Set the starting state defined for the workflow on the given workflow item
	 * @param item The workflow item 
	 * @return 
	 */
	public State start( WorkItem item ) {
		State state = (State) states.get( this.start );
		if( state == null ) {
			throw new RuntimeException( "Starting state " + this.start + " not defined in the workflow " + this.name );
		}
		item.setState( state.getName() );
		return state;
	}
	
	/**
	 * Return the current state defined for the workflow given the workflow item
	 * @param item The workflow item
	 * @return The current state
	 */
	public State getCurrentState( WorkItem item ) {
		State state = (State) states.get( item.getState() );
		if( state == null ) {
			throw new RuntimeException( "State " + item.getState() + " not defined in the workflow " + this.name );
		}
		return state;
	}
}
