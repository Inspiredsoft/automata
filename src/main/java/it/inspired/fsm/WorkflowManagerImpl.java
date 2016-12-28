package it.inspired.fsm;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Massimo Romano
 *
 */
public class WorkflowManagerImpl implements WorkflowManager {

	private final static Log log = LogFactory.getLog( WorkflowManager.class );
	
	/* Collection of managed workflow */
	private Map<String,Workflow> workflows = new HashMap<String,Workflow>();
	
	/* Starting directory where search all the workflow definition */
	private String pathname =  WorkflowManager.class.getResource("/").getPath();
	public void setPathname(String pathname) {
		this.pathname = pathname;
	}
	
	//--------------------------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------------------------
	
	public WorkflowManagerImpl() throws Exception{
		configure();
	}
	
	public WorkflowManagerImpl(String pathname) throws Exception {
		this.pathname = pathname;
		configure();
	}

	//--------------------------------------------------------------------------------------------
	
	/**
	 * Read all the workflow definition
	 * @throws Exception
	 */
	public void configure() throws Exception {
		if( log.isDebugEnabled() ) { log.debug( "Reading workflow from directory " + pathname ); }
		
		Digester digester = new Digester();
		
		/* <workflow> */
		digester.addObjectCreate( "workflow", "class", Workflow.class );
		digester.addSetProperties( "workflow" );
		digester.addSetNext( "workflow", "addWorkflow" );
		
		/* <state> */
		digester.addObjectCreate( "workflow/state", "class", State.class );
		digester.addSetProperties( "workflow/state" );
		digester.addSetNext( "workflow/state", "addState" );
		
		/* <transition> */
		digester.addObjectCreate( "workflow/state/transition", "class", Transition.class );
		digester.addSetProperties( "workflow/state/transition" );
		digester.addSetNext( "workflow/state/transition", "addTransition" );
		
		/* <script> */
		digester.addObjectCreate( "workflow/state/transition/script", "class", Script.class );
		GroovyShell shell = new GroovyShell();
		digester.addRule( "workflow/state/transition/script", new SetGroovyScriptRule( shell, "code" ) );
		digester.addSetNext( "workflow/state/transition/script", "addScript" );
		
		Collection<File> files = getFiles( new File(pathname), ".fsm.xml" );
		for( File file : files ) {
			if( log.isDebugEnabled() ) log.debug( "Lettura file: " + file );
			
			digester.clear();
			digester.push( this );
			digester.parse( file );
			
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	public void addWorkflow( Workflow workflow ) {
		if( workflows.containsKey( workflow.getName() ) ) {
			throw new RuntimeException( "Workflow " + workflow.getName() + " already defined" );
		} else {
			workflows.put( workflow.getName(), workflow );
			
			if( log.isDebugEnabled() ) log.debug( "Added workflow " + workflow.getName() );
		}
	}
	
	public Workflow getWorkflow( String name ) {
		Workflow flow = (Workflow)workflows.get( name );
		if( flow == null ) throw new RuntimeException( "Workflow " + name + " undefined" );

		return flow;
	}

	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.fsm.WorkflowManager#fire(it.inspired.fsm.Transition, it.inspired.fsm.WorkflowContext)
	 */
	public void fire( Transition transition, WorkflowContext context ) {
		
		if ( log.isDebugEnabled() ) {
			log.debug( "Executing transition " + transition.toString() + " for WorkItem " + context.getItem() );
		}
		
		Workflow workflow = transition.getState().getWorkflow();
		
		/* Script of the transition */
		if( transition.getScripts().size() > 0 ) {

			Binding binding = new Binding();
			
			/* Add context attribute to the binding */
			Iterator<String> attrs = context.keySet().iterator();
			while( attrs.hasNext() ) {
				String attrName = (String)attrs.next();
				binding.setVariable( attrName, context.getAttribute( attrName ) );
			}
			
			/* bind the workflow item and aliases it if defined in the workitem */
			binding.setVariable( workflow.getItemName() != null ? workflow.getItemName() : "item", context.getItem() );
			
			/* context */
			binding.setVariable( "context", context );
			
			/* execution */
			Iterator<Script> scripts = transition.getScripts().iterator();
			while( scripts.hasNext() ) {
				Script script =(Script)scripts.next();
				script.getCode().setBinding( binding );
				script.getCode().run();
			}	

		} else {
			if ( log.isDebugEnabled() ) {
				log.debug( "No Grovy Script defined for transition " + transition.toString() );
			}
		}
		
		/* final state */
		context.getItem().setState( transition.getTo() );
	
		if ( log.isDebugEnabled() ) {
			log.debug( "Final state for WorkItem '" + context.getItem() + "' is '" + transition.getTo() + "'" );
		}
	}
	
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
