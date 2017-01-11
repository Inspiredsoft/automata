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

import it.inspired.automata.model.Workflow;
import it.inspired.automata.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

/**
 * A configurable Workflow manager useful with IoC framework like Spring
 * 
 * @author Massimo Romano
 *
 */
public class ConfigurableWorkflowManager extends WorkflowManagerImpl {

	private final static Log log = LogFactory.getLog( WorkflowManager.class );
	
	private static final String SUFFIX = ".fsm.xml";
	
	private List<String> paths;

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}
	
	@Override
	public Workflow getWorkflow(String name) {
		if ( workflows.isEmpty() ) {
			for ( String path : paths ) {
				try {
					Collection<File> files = FileUtils.getFiles( new File( path ), SUFFIX );
					for( File file : files ) {
						super.load( file );
						
					}
				} catch (IOException e) {
					log.error( "Error accessing file " + paths, e );
				} catch (SAXException e) {
					log.error( "Error parsing file " + paths, e );
				}
			}
		}
		return super.getWorkflow(name);
	}
}
