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

import java.io.File;
import java.io.IOException;

import groovy.lang.GroovyShell;
import it.inspired.automata.model.Script;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

public class WorkflowParser {
	
	private final static Log log = LogFactory.getLog( WorkflowParser.class );
	
	private static Digester digester = null;
	
	static{
		digester = new Digester();
		
		/* <workflow> */
		digester.addObjectCreate( "workflow", "class", Workflow.class );
		digester.addSetProperties( "workflow" );
		//digester.addSetNext( "workflow", "addWorkflow" );
		
		/* <state> */
		digester.addObjectCreate( "workflow/state", "class", State.class );
		digester.addSetProperties( "workflow/state" );
		digester.addSetNext( "workflow/state", "addState" );
		
		/* <transition> */
		digester.addObjectCreate( "workflow/state/transition", "class", Transition.class );
		digester.addSetProperties( "workflow/state/transition" );
		digester.addSetNext( "workflow/state/transition", "addTransition" );
		
		/* <condition> */
		GroovyShell shell = new GroovyShell();
		digester.addObjectCreate( "workflow/state/transition/condition", "class", Script.class );
		digester.addRule( "workflow/state/transition/condition", new SetGroovyScriptRule( shell, "code" ) );
		digester.addSetNext( "workflow/state/transition/condition", "setCondition" );

		/* <script> */
		digester.addObjectCreate( "workflow/state/transition/action", "class", Script.class );
		digester.addRule( "workflow/state/transition/action", new SetGroovyScriptRule( shell, "code" ) );
		digester.addSetNext( "workflow/state/transition/action", "addAction" );
		
	}
	
	public static Workflow parse(File file) throws IOException, SAXException {
		if( log.isDebugEnabled() ) log.debug( "Parsing file: " + file );
		digester.clear();
		return (Workflow) digester.parse( file );
	}
}
