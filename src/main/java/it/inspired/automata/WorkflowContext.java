package it.inspired.automata;

import java.util.HashMap;

/**
 * Represent a context for a workflow. 
 * It is associated to a WorkItem ad has a set of context attribute.
 * 
 * @author Massimo Romano
 *
 */
public class WorkflowContext extends HashMap<String,Object> {

	private static final long serialVersionUID = -5871692354258127274L;

	/* The workflow item associated to the context */
	private WorkItem item;
	
	/**
	 * Cannot exist a context without the associated workflow item
	 * @param item The workflow item int the context
	 */
	public WorkflowContext(WorkItem item) {
		this.item = item;
	}
	
	/**
	 * Return the item in the current context
	 * @return The associated workflow item
	 */
	public WorkItem getItem() {
		return item;
	}

	/**
	 * Set an attribute in the context
	 * @param key The attribute name
	 * @param value The attribute value
	 */
	public void setAttribute( String key, Object value ) {
		super.put( key, value );
	}

	/**
	 * Return an attribute value
	 * @param attrName The name of the attribute
	 * @return Attribute value
	 */
	public Object getAttribute(String attrName) {
		return super.get( attrName );
	}

	/**
	 * Remove an attribute from the context
	 * @param attrName The name of the attribute to remove
	 * @return The attribute removed
	 */
	public Object removeAttribute( String attrName ) {
		return super.remove( attrName );
	}
	
	/**
	 * Check if an attribute is present in the context
	 * @param attrName The attribute name
	 * @return True if the attribute is present
	 */
	public boolean hasAttribute( String attrName ) {
		return super.containsKey( attrName );
	}
}
