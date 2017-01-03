package it.inspired.automata.utils;

import groovy.lang.Binding;
import it.inspired.automata.model.Script;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;

import java.util.Iterator;
import java.util.List;

/**
 * Utility class for groovy
 * 
 * @author Massimo Romano
 *
 */
public class GroovyUtils {
	
	/**
	 * Create a groovy binder from context
	 * 
	 * @param workflow Workflow declaring the item name
	 * @param context Context from witch take the item
	 * @return
	 */
	public static Binding createBuilder( Workflow workflow, WorkflowContext context ) {

		Binding binding = new Binding();
		
		/* context */
		binding.setVariable( "context", context );
		
		/* Add context attribute to the binding */
		Iterator<String> attrs = context.keySet().iterator();
		while( attrs.hasNext() ) {
			String attrName = (String)attrs.next();
			binding.setVariable( attrName, context.getAttribute( attrName ) );
		}
		
		/* bind the workflow item and aliases it if defined in the workitem */
		binding.setVariable( workflow.getItemName() != null ? workflow.getItemName() : "item", context.getItem() );
		
		return binding;
	}
	
	/**
	 * Execute a script using binding context
	 * @param binding
	 * @param script
	 * @return
	 */
	public static Object execute( Binding binding, Script script ) {
		script.getCode().setBinding( binding );
		return script.getCode().run();
	}
	
	/**
	 * Executes all the script in the list 
	 * @param binding
	 * @param actions
	 */
	public static void execute( Binding binding, List<Script> actions ) {
		/* execution all actions */
		Iterator<Script> iterator = actions.iterator();
		while( iterator.hasNext() ) {
			Script action = (Script) iterator.next();
			execute( binding, action );
		}	
	}

	/**
	 * Evaluate a script to boolean
	 * @param binding
	 * @param script
	 * @return
	 */
	public static Boolean evaluate( Binding binding, Script script ) {
		return (Boolean) execute( binding, script );
	}
}
