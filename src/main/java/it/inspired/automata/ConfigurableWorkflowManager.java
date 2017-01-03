package it.inspired.automata;

import it.inspired.automata.model.Workflow;

import java.io.File;
import java.io.IOException;
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
					super.load( new File( path ) );
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
