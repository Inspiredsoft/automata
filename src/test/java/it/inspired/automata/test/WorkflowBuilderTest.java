package it.inspired.automata.test;

import it.inspired.automata.ClasspathWorkflowManager;
import it.inspired.automata.WorkflowBuilder;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.exception.NoTransitionAllowed;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.test.builder.Item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkflowBuilderTest {

	private WorkflowManager manager 	= null;
	
	@Before
	public void before() {
		try {
			manager = new ClasspathWorkflowManager();
		} catch (Exception e) {
			Assert.fail();
		}
		
		Assert.assertNotNull( manager );	
	}
	
	@Test
	public void newWorkflow() {
		Workflow workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.build();
		
		Assert.assertEquals( workflow.getName(), "testws" );
		
		Assert.assertEquals( workflow.getItemName(), "item" );
	}
	
	@Test
	public void addStates() {
		Workflow workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.addStartState( "A", "State A" )
				.build();
		
		Assert.assertNotNull( workflow.getStart() );
		
		Assert.assertEquals( workflow.getStart(), "A" );
		
		Assert.assertEquals( workflow.getState( "A" ).getName(), "A" );
		
		Assert.assertEquals( workflow.getState( "A" ).getDescription(), "State A" );
		
		Assert.assertTrue( workflow.getStates().size() == 1 );
		
		workflow = WorkflowBuilder
					.fromWorkflow( workflow )
					.addState( "B", "State B")
					.build();
		
		Assert.assertTrue( workflow.getStates().size() == 2 );
		
		Assert.assertEquals( workflow.getState( "B" ).getName(), "B" );
		
		Assert.assertEquals( workflow.getState( "B" ).getDescription(), "State B" );
	}
	
	@Test
	public void addTransitions() {
		Workflow workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.addStartState( "A", "State A" )
				.addState( "B", "State B")
				.addTransition( "From_A_To_B", "A", "B" )
				.build();
		
		Assert.assertTrue( workflow.getState( "A" ).getTransitions().size() == 1 );
		
		Assert.assertEquals( workflow.getState( "A" ).getTransition( "From_A_To_B" ).getTo(), "B" ); 
	}
	
	@Test
	public void executionOk() throws NoTransitionAllowed {
		
		Workflow workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.addStartState( "A", "State A" )
				.addState( "B", "State B")
				.addTransition( "From_A_To_B", "A", "B" )
				.addTransition( "From_B_To_A", "B", "A" )
				.addCondition( "From_A_To_B", "return true;" )
				.build(); 

		Assert.assertNotNull( workflow );
		
		Item item = new Item();
		WorkflowContext context = new WorkflowContext( item );

		State state = workflow.start(item);
		Assert.assertEquals( state.getName(), "A" );
		
		state = manager.submit( workflow, context );
		Assert.assertEquals( state.getName(), "B" );
		
		state = manager.submit( workflow, context );
		Assert.assertEquals( state.getName(), "A" );

	}
	
	@Test( expected = NoTransitionAllowed.class )
	public void executionKo() throws NoTransitionAllowed {
		
		Workflow workflow = WorkflowBuilder
				.newWorkflow( "testws", "item")
				.addStartState( "A", "State A" )
				.addState( "B", "State B")
				.addTransition( "From_A_To_B", "A", "B" )
				.addTransition( "From_B_To_A", "B", "A" )
				.addCondition( "From_A_To_B", "return false;" )
				.build(); 

		Assert.assertNotNull( workflow );
		
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
