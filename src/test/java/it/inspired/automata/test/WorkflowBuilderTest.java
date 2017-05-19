package it.inspired.automata.test;

import it.inspired.automata.ClasspathWorkflowManager;
import it.inspired.automata.WorkflowBuilder;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.test.builder.Item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkflowBuilderTest {

	private WorkflowManager manager 	= null;
	private Workflow 		workflow 	= null;
	
	@Before
	public void before() {
		workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.addStartState( "A", "State A" )
				.addState( "B", "State B")
				.addTransition( "From_A_To_B", "A", "B" )
				.addTransition( "From_B_To_A", "B", "A" )
				.addCondition( "From_A_To_B", "return true;" )
				.addAction( "From_A_To_B", "println 'Going to B';" )
				.addAction( "From_B_To_A", "println 'Going back to A';" )
				.build(); 

		Assert.assertNotNull( workflow );
		
		try {
			manager = new ClasspathWorkflowManager();
		} catch (Exception e) {
			Assert.fail();
		}
		
		Assert.assertNotNull( manager );	
	}
	
	@Test
	public void execution() {
		Item item = new Item();
		WorkflowContext context = new WorkflowContext( item );

		State state = workflow.start(item);
		Assert.assertEquals( state.getName(), "A" );
		
		state = manager.submit( workflow, context );
		Assert.assertEquals( state.getName(), "B" );
		
		state = manager.submit( workflow, context );
		Assert.assertEquals( state.getName(), "A" );

	}
}
