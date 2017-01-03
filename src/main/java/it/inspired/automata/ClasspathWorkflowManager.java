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

import java.io.File;
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
		String path = WorkflowManager.class.getResource("/").getPath();
		
		if( log.isDebugEnabled() ) { log.debug( "Reading workflow from directory " + path ); }
		
		Collection<File> files = getFiles( new File( path ), SUFFIX );
		for( File file : files ) {
			super.load( file );
			
		}
	}
	
	//--------------------------------------------------------------------------------------------
	// Private Methods
	//--------------------------------------------------------------------------------------------
	
	private Collection<File> getFiles(File path, String suffix) {
		Collection<File> result = new Vector<File>();
		if( path.isDirectory() ) {
			String[] filenames = path.list();
			for( int i = 0; i < filenames.length; i++ ) {
				File file = new File( path.getAbsolutePath() + File.separator + filenames[ i ] );
	
				if( file.getName().endsWith( suffix ) ) {
					result.add( file );
				} else if( file.isDirectory() ) {
					result.addAll( getFiles( file, suffix ) );
				}
			}
		} else {
			result.add( path );
		}
		return result;
	}

}
