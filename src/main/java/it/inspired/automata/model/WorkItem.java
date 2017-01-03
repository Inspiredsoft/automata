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
	public String getState();
	
	/**
	 * Set the item state
	 * @param state The state to set
	 */
	public void setState( String state );
}
