package it.inspired.automata.test;

import it.inspired.automata.ClasspathWorkflowManager;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.test.recognition.ZeroRecognition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZeroRecognitionTest {
	
	private static final String WF_NAME = "zeroRecognition";
	private WorkflowManager manager 	= null;
	private Workflow 		workflow 	= null;
	
	@Before
	public void before() throws Exception {
		manager = new ClasspathWorkflowManager();
		
		Assert.assertNotNull( manager );
		
		workflow = manager.getWorkflow( WF_NAME );
		
		Assert.assertNotNull( workflow );
	}
 	
	@Test
	public void initialState() {
		ZeroRecognition item = new ZeroRecognition();

		State state = workflow.start( item );
		
		Assert.assertEquals( state.getName(), "Even" );
	}
	
	@Test
	public void transitionToOdd() {
		ZeroRecognition item = new ZeroRecognition();
		WorkflowContext context = new WorkflowContext( item );

		State state = workflow.start( item );
		
		state = manager.fire( state, "0", context );
		
		Assert.assertEquals( state.getName(), "Odd" );
	}
	
	@Test
	public void transitionToEven() {
		ZeroRecognition item = new ZeroRecognition();
		WorkflowContext context = new WorkflowContext( item );

		State state = workflow.start( item );
		
		state = manager.fire( state, "1", context );
		
		Assert.assertEquals( state.getName(), "Even" );
	}
	
	@Test
	public void transitionToOddAndEven() {
		ZeroRecognition item = new ZeroRecognition();
		WorkflowContext context = new WorkflowContext( item );

		State state = workflow.start( item );
		
		state = manager.fire( state, "0", context );
		
		Assert.assertEquals( state.getName(), "Odd" );
		
		state = manager.fire( state, "1", context );
		
		Assert.assertEquals( state.getName(), "Odd" );
		
		state = manager.fire( state, "0", context );
		
		Assert.assertEquals( state.getName(), "Even" );
	}
}
