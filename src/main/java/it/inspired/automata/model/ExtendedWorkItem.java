package it.inspired.automata.model;

import java.util.Date;

public interface ExtendedWorkItem extends WorkItem {
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
}
