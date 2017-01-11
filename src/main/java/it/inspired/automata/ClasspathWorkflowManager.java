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

package it.inspired.automata;

import it.inspired.automata.utils.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClasspathWorkflowManager extends WorkflowManagerImpl {

	private final static Log log = LogFactory.getLog( WorkflowManager.class );
	
	private static final String SUFFIX = ".fsm.xml";

	public ClasspathWorkflowManager() throws Exception {
		super();
		configure();
	}
	
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Read all the workflow definition
	 * @throws Exception
	 */
	protected void configure() throws Exception {
		URL url = Thread.currentThread().getContextClassLoader().getResource( "" );
		String path = url.getPath();
		
		if( log.isDebugEnabled() ) { log.debug( "Reading workflow from directory " + path ); }
		
		Collection<File> files = FileUtils.getFiles( new File( path ), SUFFIX );
		for( File file : files ) {
			super.load( file );
			
		}
	}
	
	//--------------------------------------------------------------------------------------------
	// Private Methods
	//--------------------------------------------------------------------------------------------
	
	

}
