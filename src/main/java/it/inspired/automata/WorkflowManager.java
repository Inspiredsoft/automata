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

package it.inspired.automata;

import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;

import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

/**
 * Interface for a workflow manager
 * 
 * @author Massimo Romano
 *
 */
public interface WorkflowManager {

	/**
	 * Return a workflow by name
	 * @param name Name of the workflow
	 * @return The required workflow
	 */
	public Workflow getWorkflow( String name );
	
	/**
	 * Load the finite state machine from the file
	 * 
	 * @param file The xml file representing the fsm
	 * @throws IOException 
	 * @throws SAXException
	 */
	public void load( File file ) throws IOException, SAXException;
	
	/**
	 * Fire a transition on the specified context. 
	 * The condition is not evaluated and the action is always executed.
	 * 
	 * @param transition The transition to fire.
	 * The condition is not evaluated and the action is always executed.
	 * 
	 * @param context A workflow context
	 * @return The state reached
	 */
	public State fire( Transition transition, WorkflowContext context );
	
	/**
	 * Fire a transition name on the context starting from the state 
	 * @param state Starting state
	 * @param transitionName Name of the transition
	 * @param context A workflow context
	 * @return The reached state
	 */
	public State fire( State state, String transitionName, WorkflowContext context );
	
	/**
	 * Fire the first transition having condition true in the specified workflow
	 * 
	 * @param workFlow The workflow to use
	 * @param context A workflow context
	 * @return The reached state
	 */
	public State submit( Workflow workFlow, WorkflowContext context );
	
	/**
	 * Check if the condition of the transition is true or not
	 * @param transition The transition to evaluate
	 * @param context A workflow context
	 * @return True if the transition do not have condition or if the condition is evaluated to true
	 */
	public boolean evaluateCondition( Transition transition, WorkflowContext context );
}
