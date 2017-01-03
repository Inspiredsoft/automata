package it.inspired.automata.model;

import java.util.Collection;
import java.util.Date;

public interface ExtendedWorkItem extends WorkItem {
	/**
	 * Return the starting time of the state
	 * @return The starting time
	 */
	public Date getStateTime();
	
	/**
	 * Set the starting time of the state
	 * @param date The starting date
	 */
	public void setStateTime( Date date );
	
	/**
	 * Return the history of the item 
	 * @return The history of the item
	 */
	public Collection<HistoryItem> getHistory();
	
	/**
	 * Set the history of the item
	 * @param history The history to set
	 */
	public void setHistory( Collection<HistoryItem> history );
}
