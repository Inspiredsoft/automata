/*******************************************************************************
* Inspired Automata is a framework implement a Finite State machine.
* Copyright (C) 2017 Inspired Soft
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.    
*******************************************************************************/

package it.inspired.automata.model;

import java.util.Calendar;
import java.util.Collection;
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
	private String itemName;
	
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
	 * Return all the states defined in the workflow
	 * @return A collection of state
	 */
	public Collection<State> getStates() {
		return states.values();
	}
	
	/**
	 * Return the state defined in the workflow by name
	 * @param name Name of the state
	 * @return The state defined in the workflow
	 */
	public State getState( String name ) {
		return (State)states.get( name );
	}
	
	/**
	 * Return the state defined in the workflow by item
	 * @param item The work item in the state required
	 * @return The state defined in the workflow
	 */
	public State getState( WorkItem item ) {
		return (State)states.get( item.getState() );
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
	 * Set the starting state defined for the workflow on the given workflow item
	 * @param item The workflow item 
	 * @return 
	 */
	public State start( ExtendedWorkItem<?> item ) {
		State state = start( (WorkItem)item );
		item.setStateTime( Calendar.getInstance().getTime() );
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
