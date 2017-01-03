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
 * A script can be an action or a condition in a transition
 * 
 * @author Massimo Romano
 *
 */
public class Script {
	
	/* Groovy script */
	private groovy.lang.Script code;
	
	/* Transition */
	private Transition transition;

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public groovy.lang.Script getCode() {
		return code;
	}

	public void setCode(groovy.lang.Script groovyScript) {
		this.code = groovyScript;
	}
	
}
