package it.inspired.automata;

import groovy.lang.GroovyShell;
import it.inspired.automata.model.Script;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;

/**
 * Builder class for a workflow 
 * 
 * @author Massimo Romano
 *
 */
public class WorkflowBuilder {
	/* The builded workflow */
	private Workflow workflow = null;
	private GroovyShell shell = new GroovyShell();
	
	/**
	 * Private constructor
	 */
	private WorkflowBuilder() {}
	
	/**
	 * Its the first method to call to update a worlflow
	 * @param workflow The starting workflow
	 * @return The builder
	 */
	public static WorkflowBuilder fromWorkflow( Workflow workflow ) {
		WorkflowBuilder builder = new WorkflowBuilder();
		
		builder.workflow = workflow;
		
		return builder;
	}
	
	/**
	 * Is the first metod to call that generate the workflow.
	 * @param name Name of the workflow
	 * @param itemName Name of the workflow item
	 * @return This builder
	 */
	public static WorkflowBuilder newWorkflow( String name, String itemName ) {
		WorkflowBuilder builder = new WorkflowBuilder();
		
		builder.workflow = new Workflow();
		builder.workflow.setName( name );
		builder.workflow.setItemName( itemName );
		
		return builder;
	}
	
	/**
	 * Is the first metod to call that generate the workflow.
	 * @param name Name of the workflow
	 * @return This builder
	 */
	public static WorkflowBuilder newWorkflow( String name ) {
		return newWorkflow( name, null );
	}
	
	/**
	 * Build the workflow
	 * @return The workflow generated
	 */
	public Workflow build() {
		return workflow;
	}
	
	/**
	 * Add the start state to the workflow. Can be invoked one time only.
	 * @param stateName The name of the start state
	 * @param description The description of the start state
	 * @return This builder
	 */
	public WorkflowBuilder addStartState( String stateName, String description ) {
		if ( workflow.getStart() != null && !workflow.getStart().isEmpty() ) {
			throw new RuntimeException( "the start state is already defined" );
		}
		State state = newState( stateName, description  );
		workflow.setStart( stateName );
		workflow.addState( state );
		return this;		
	}
	
	/**
	 * Add a state to the workflow.
	 * @param stateName The state name
	 * @param description The state description
	 * @return This builder
	 */
	public WorkflowBuilder addState( String stateName, String description ) {
		State state = newState( stateName, description  );
		workflow.addState( state );
		return this;
	}
	
	/**
	 * Add a transition to the workflow.
	 * @param name The name of the transition
	 * @param startState The start state of the transition
	 * @param toState The final state of the transition
	 * @return This builder
	 */
	public WorkflowBuilder addTransition( String name, String startState, String toState ) {
		return addTransition(name, null, startState, toState);
	}
	
	/**
	 * 
	 * Add a transition to the workflow.
	 * @param name The name of the transition
	 * @param description The description of the transition
	 * @param startState The start state of the transition
	 * @param toState The final state of the transition
	 * @return This builder
	 */
	public WorkflowBuilder addTransition( String name, String description, String startState, String toState ) {
		State start = workflow.getState( startState );
		
		if ( start == null ) {
			throw new RuntimeException( "The 'start' state " + startState + " is undefined" );
		}
		
		State to = workflow.getState( toState );
		
		if ( to == null ) {
			throw new RuntimeException( "The 'to' state " + toState + " is undefined" );
		}
		
		newTransition( name, description, start, to.getName() );
		return this;
	}
	
	/**
	 * Add a condition to the transition.
	 * @param transitionName The transition name
	 * @param condition The groovy script for the condition
	 * @return This builder
	 */
	public WorkflowBuilder addCondition( String transitionName, String condition ) {
		Transition transition = findTransiton( transitionName );
		
		if ( transition == null ) {
			throw new RuntimeException( "Transition " + transitionName + " is undefined" );
		}
		
		if ( transition.getCondition() != null ) {
			throw new RuntimeException( "Condition for transition " + transitionName + " is already defined" );
		}
		
		Script script = new Script();
		script.setCode( shell.parse( condition ) );
		transition.setCondition( script );
	
		return this;
	}
	
	/**
	 * Add a new action to a transition
	 * @param transitionName The transition name
	 * @param condition The groovy script for the action
	 * @return This builder
	 */
	public WorkflowBuilder addAction( String transitionName, String condition ) {
		Transition transition = findTransiton( transitionName );
		if ( transition == null ) {
			throw new RuntimeException( "Transition " + transitionName + " is undefined" );
		}
		Script script = new Script();
		script.setCode( shell.parse( condition ) );
		transition.addAction( script );
	
		return this;
	}
	
	//------------------------------------------------------------------------------------------
	// Private mwthods
	//------------------------------------------------------------------------------------------
	
	private State newState( String stateName, String description ) {
		State state = new State();
		state.setName( stateName );
		state.setDescription( description );
		state.setWorkflow( workflow );
		return state;
	}
	
	private Transition newTransition( String name, String description, State state, String to) {
		Transition transition = new Transition();
		transition.setName(name);
		transition.setState(state);
		transition.setTo(to);	
		transition.setDescription( description );
		
		state.addTransition( transition );
		
		return transition;
	}
	
	private Transition findTransiton( String name ) {
		Transition transition = null;
		for ( State state : workflow.getStates() ) {
			transition = state.getTransition( name );
			if ( transition != null ) {
				break;
			}
		}
		return transition;
	}
	
}
