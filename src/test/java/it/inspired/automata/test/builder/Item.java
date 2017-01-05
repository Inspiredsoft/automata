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

package it.inspired.automata.test.builder;

import it.inspired.automata.model.WorkItem;

public class Item implements WorkItem {
	String state;

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}
}
