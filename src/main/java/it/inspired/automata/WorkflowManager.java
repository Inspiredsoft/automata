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

import it.inspired.automata.exception.NoTransitionAllowed;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

import java.io.File;

/**
 * Interface for a workflow manager
 * 
 * @author Massimo Romano
 *
 */
public interface WorkflowManager {
	
	/**
	 * Add a new Managed Workflow
	 * @param workflow The workflow to add
	 */
	public void addWorkflow( Workflow workflow );

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
	 * @throws Exception
	 * @return The loaded workflow
	 */
	public Workflow load( File file ) throws Exception;
	
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
	 * @throws NoTransitionAllowed Returned if no transition is allowed
	 */
	public State submit( Workflow workFlow, WorkflowContext context ) throws NoTransitionAllowed;
	
	/**
	 * Check if the condition of the transition is true or not
	 * @param transition The transition to evaluate
	 * @param context A workflow context
	 * @return True if the transition do not have condition or if the condition is evaluated to true
	 */
	public boolean evaluateCondition( Transition transition, WorkflowContext context );
}
