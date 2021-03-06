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

package it.inspired.automata.test.recognition;

import it.inspired.automata.WorkflowManager;
import it.inspired.automata.WorkflowManagerImpl;
import it.inspired.automata.model.State;
import it.inspired.automata.model.WorkItem;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

import java.io.File;

public class ZeroRecognition implements WorkItem {
	
	private static final String SEQUENCE = "01001101";
	private static final String FSM_PATH = "/Users/massimo/Workspace/inspired-open/automata/src/test/resources/zero-recognition.fsm.xml";

	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public static void main( String[] args ) throws Exception {
		
		/* Load the FSM */
		WorkflowManager manager = new WorkflowManagerImpl();
		Workflow wf = manager.load( new File( FSM_PATH ) );
		
		/* Set the initial state */
		ZeroRecognition machine = new ZeroRecognition();
		State state = wf.start( machine );
			
		/* Create context */
		WorkflowContext context = new WorkflowContext( machine );
		
		for ( int i = 0; i < SEQUENCE.length(); i++ ) {
			state = manager.fire( state, String.valueOf( SEQUENCE.charAt( i ) ), context );
		}
		
		System.out.println( machine.state + ": " + state.getDescription() );
				
	}

}
