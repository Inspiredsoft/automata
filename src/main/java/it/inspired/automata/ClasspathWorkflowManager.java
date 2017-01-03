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
