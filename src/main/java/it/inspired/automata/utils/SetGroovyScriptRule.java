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
