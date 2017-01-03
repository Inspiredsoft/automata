package it.inspired.automata.model;

import java.util.Collection;
import java.util.Date;

public interface ExtendedWorkItem<E extends HistoryItem> extends WorkItem {
	/**
	 * Return the starting time of the state
	 * @return The starting time
	 */
	public Date getStateTime();
	
	/**
	 * Set the starting time of the state
	 * @param stateTime The starting date
	 */
	public void setStateTime( Date stateTime );
	
	/**
	 * Return all the history of the item
	 * @return The history
	 */
	public Collection<E> getHistories();
}
