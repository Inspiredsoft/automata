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

package it.inspired.automata.test.builder;

import it.inspired.automata.WorkflowBuilder;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.WorkflowManagerImpl;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

public class MainBuilder {

	public static void main( String[] args) {
		Workflow wf = WorkflowBuilder
						.newWorkflow( "testws", "item")
						.addStartState( "A", "State A" )
						.addState( "B", "State B")
						.addTransition( "From_A_To_B", "A", "B" )
						.addTransition( "From_B_To_A", "B", "A" )
						.addCondition( "From_A_To_B", "return false;" )
						.addAction( "From_A_To_B", "println 'Going to B';" )
						.addAction( "From_B_To_A", "println 'Going back to A';" )
						.build(); 
		
		WorkflowManager manager = new WorkflowManagerImpl();
		
		Item item = new Item();
		wf.start(item);
		
		WorkflowContext context = new WorkflowContext( item );
		
		State state = manager.submit( wf, context );
		
		System.out.println( state.getDescription() );
		
		state = manager.submit( wf, context );
		
		System.out.println( state.getDescription() );
	}
}
