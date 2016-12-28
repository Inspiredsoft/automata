package it.inspired.automata;

/**
 * Interface for a workflow manager
 * 
 * @author Massimo Romano
 *
 */
public interface WorkflowManager {

	/**
	 * Return a workflow by name
	 * @param name Name of the workflow
	 * @return The required workflow
	 */
	public Workflow getWorkflow( String name );
	
	/**
	 * Fire a transition on the specified context. 
	 * The condition is not evaluated and the action is always executed.
	 * 
	 * @param transition The transition to fire.
	 * The condition is not evaluated and the action is always executed.
	 * 
	 * @param context A workflow context
	 */
	public void fire( Transition transition, WorkflowContext context );
	
	/**
	 * Fire a transition name on the context starting from the state 
	 * @param state Starting state
	 * @param transitionName Name of the transition
	 * @param context A workflow context
	 */
	public void fire( State state, String transitionName, WorkflowContext context );
	
	/**
	 * Fire the first transition having condition true in the specified workflow
	 * @param workFlow The workflow to use
	 * @param context A workflow context
	 */
	public void fire( Workflow workFlow, WorkflowContext context );
	
	/**
	 * Check if the condition is for the transition is true or not
	 * @param transition The transition to evaluate
	 * @param context A workflow context
	 * @return True if the transition do not have condition or if the condition is evaluated to true
	 */
	public boolean evaluateCondition( Transition transition, WorkflowContext context );
}
