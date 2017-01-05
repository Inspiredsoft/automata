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

package it.inspired.automata.test.ads;

import it.inspired.automata.ClasspathWorkflowManager;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.WorkItem;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

public class AutomaticDoor implements WorkItem {
	
	private String state;
	private Boolean ringBell = false;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;	
	}

	public Boolean getRingBell() {
		return ringBell;
	}

	public void setRingBell(Boolean ringBell) {
		this.ringBell = ringBell;
	}
	
	public static void main( String[] args ) throws Exception {
		/* Load all the FSM in the classpath */
		WorkflowManager manager = new ClasspathWorkflowManager();
		
		/* Set the initial state */
		AutomaticDoor door = new AutomaticDoor();
		Workflow wf = manager.getWorkflow( "automaticDoorSystem" );
		State state = wf.start( door );
		
		/* Create context */
		WorkflowContext context = new WorkflowContext( door );
		
		/* Open door signal */
		Transition openTransition = state.getTransition( "open" );
		state = manager.fire( openTransition, context );
		ring( door );
		
		/* Close door signal */
		Transition closeTransition = state.getTransition( "close" );
		state = manager.fire( closeTransition, context );		
		ring( door );
		
	}
	
	/**
	 * Print the ring message
	 * @param door The door
	 */
	private static void ring( AutomaticDoor door ) {
		if ( door.getRingBell() ) {			
			System.out.println( "Ringing ...." );
			try {Thread.sleep( 1000 );} catch (InterruptedException e) {}
		} else {
			System.out.println( "Stop ringing ...." );
		}
	}
	
}
