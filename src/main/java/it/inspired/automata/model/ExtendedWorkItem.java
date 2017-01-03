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
