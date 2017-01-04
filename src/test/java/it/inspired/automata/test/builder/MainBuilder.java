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
						.newWorkflow( "test", "item")
						.addStartState( "A", "State A" )
						.addState( "B", "State B")
						.addTransition( "From_A_To_B", "A", "B" )
						.addTransition( "From_B_To_A", "B", "A" )
						.addCondition( "From_A_To_B", "return true;" )
						.addAction( "From_A_To_B", "println 'Going to B';" )
						.addAction( "From_B_To_A", "println 'Going back to A';" )
						.build(); 
		
		WorkflowManager manager = new WorkflowManagerImpl();
		manager.addWorkflow( wf );
		
		Item item = new Item();
		wf.start(item);
		
		WorkflowContext context = new WorkflowContext( item );
		
		State state = manager.submit( wf, context );
		
		System.out.println( state.getDescription() );
		
		state = manager.submit( wf, context );
		
		System.out.println( state.getDescription() );
	}
}
