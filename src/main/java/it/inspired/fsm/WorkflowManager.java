package it.inspired.fsm;

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
	 * Fire a transition on the specified context
	 * @param transition The transition to fire
	 * @param context A workflow context
	 */
	public void fire( Transition transition, WorkflowContext context );

}
