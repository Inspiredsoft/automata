package it.inspired.automata.model;

import java.util.Date;

/**
 * Log the duration of a state for an item
 * 
 * @author Massimo Romano
 *
 */
public interface HistoryItem {
	/**
	 * Return the state of the current item history
	 * @return The current state of the history
	 */
	public String getState();
	
	/**
	 * Set the item state of the current item history
	 * @param state The state to set
	 */
	public void setState( String state );
	
	/**
	 * Get the start date of the history
	 * @return The start date
	 */
	public Date getStartTime();
	
	/**
	 * Set the start state of the history
	 * @param startTime The date to set
	 */
	public void setStartTime( Date startTime );
	
	/**
	 * Get the end date of the history
	 * @return The end date
	 */
	public Date getEndTime();
	
	/**
	 * Set the end state of the history
	 * @param endTime The date to set
	 */
	public void setEndTime( Date endTime );
}
