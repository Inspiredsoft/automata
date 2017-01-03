/*******************************************************************************
* Inspired Automata is a framework implement a Finite State machine.
* Copyright (C) 2017 Inspired Soft
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.    
*******************************************************************************/

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
