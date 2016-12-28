package it.inspired.automata;

/**
 * A class that implement WorkItem interface can be managed by a workflow.
 * The state property is defined by the transition defined in the workflow definition. 
 * 
 * @author Massimo Romano
 *
 */
public interface WorkItem {
	/**
	 * Return the item state 
	 * @return The current state of the item
	 */
	String getState();
	
	/**
	 * Set the item state
	 * @param state The state to set
	 */
	void setState( String state );
}