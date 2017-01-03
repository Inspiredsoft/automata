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

package it.inspired.automata.utils;

import groovy.lang.GroovyShell;
import it.inspired.reflection.Reflection;

import org.apache.commons.digester.SetNestedPropertiesRule;

/**
 * Role to parse the script tag
 * 
 * @author Massimo Romano
 *
 */
public class SetGroovyScriptRule extends SetNestedPropertiesRule {
	/* A groovy shell */
	private GroovyShell shell = null;
	
	/* The name of the method to associate to the script */
	private String methodname = null;
	
	public SetGroovyScriptRule( GroovyShell shell, String methodName ) {
		this.shell = shell;
		this.methodname = methodName;
	}
	
	@Override
	public void body(String body) throws Exception {
		super.body(body);
		
		/* Add the body as a groovy script for the method name */
		Object obj = digester.peek();
		Reflection.setProperty( obj, methodname, shell.parse( body ) );
	}
}
