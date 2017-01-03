package it.inspired.automata.test.recognition;

import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;

import it.inspired.automata.WorkflowManager;
import it.inspired.automata.WorkflowManagerImpl;
import it.inspired.automata.model.State;
import it.inspired.automata.model.WorkItem;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

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
	
	public static void main( String[] args ) throws IOException, SAXException {
		
		/* Load the FSM */
		WorkflowManager manager = new WorkflowManagerImpl();
		manager.load( new File( FSM_PATH ) );
		
		/* Set the initial state */
		ZeroRecognition machine = new ZeroRecognition();
		Workflow wf = manager.getWorkflow( "zeroRecognition" );
		State state = wf.start( machine );
			
		/* Create context */
		WorkflowContext context = new WorkflowContext( machine );
		
		for ( int i = 0; i < SEQUENCE.length(); i++ ) {
			state = manager.fire( state, String.valueOf( SEQUENCE.charAt( i ) ), context );
		}
		
		System.out.println( machine.state );
				
	}

}
