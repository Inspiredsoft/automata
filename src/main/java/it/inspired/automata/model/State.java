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
