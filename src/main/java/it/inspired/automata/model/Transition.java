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
