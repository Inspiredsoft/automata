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

import groovy.lang.Binding;
import it.inspired.automata.exception.NoTransitionAllowed;
import it.inspired.automata.model.ExtendedWorkItem;
import it.inspired.automata.model.HistoryItem;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.WorkItem;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.utils.GroovyUtils;
import it.inspired.automata.utils.WorkflowParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

/**
 * 
 * @author Massimo Romano
 *
 */
public class WorkflowManagerImpl implements WorkflowManager {

	private final static Log log = LogFactory.getLog( WorkflowManager.class );
	
	/* Collection of managed workflow */
	protected Map<String,Workflow> workflows = new HashMap<String,Workflow>();
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.automata.WorkflowManager#addWorkflow(it.inspired.automata.model.Workflow)
	 */
	public void addWorkflow( Workflow workflow ) {
		if( workflows.containsKey( workflow.getName() ) ) {
			throw new RuntimeException( "Workflow " + workflow.getName() + " already defined" );
		} else {
			workflows.put( workflow.getName(), workflow );
			
			if( log.isDebugEnabled() ) log.debug( "Added workflow " + workflow.getName() );
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.automata.WorkflowManager#getWorkflow(java.lang.String)
	 */
	public Workflow getWorkflow( String name ) {
		Workflow flow = (Workflow)workflows.get( name );
		
		if( flow == null ) throw new RuntimeException( "Workflow " + name + " undefined" );

		return flow;
	}
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.automata.WorkflowManager#load(java.io.File)
	 */
	public Workflow load( File file ) throws IOException, SAXException {
		if( log.isDebugEnabled() ) log.debug( "Lettura file: " + file );
		
		Workflow wf = WorkflowParser.parse(file);
		this.addWorkflow(wf);
		
		return wf;
	}

	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.fsm.WorkflowManager#fire(it.inspired.fsm.State, java.lang.String, it.inspired.fsm.WorkflowContext)
	 */
	public State fire( State state, String transitionName, WorkflowContext context ) {
		Transition transition = state.getTransition( transitionName  );
		if ( transition == null ) {
			throw new RuntimeException( "Transition " + transitionName + " undefined on state " + state.getName() );
		}
		return fire( transition, context );
	}
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.fsm.WorkflowManager#fire(it.inspired.fsm.Transition, it.inspired.fsm.WorkflowContext)
	 */
	public State fire( Transition transition, WorkflowContext context ) {
		
		if ( log.isDebugEnabled() ) {
			log.debug( "Executing transition " + transition.toString() + " for WorkItem " + context.getItem() );
		}
		
		/* Recover the workflow definition from the state of the transition */
		Workflow workflow = transition.getState().getWorkflow();
		
		/* Script of the transition */
		if( transition.getActions().size() > 0 ) {

			/* Create binding for glroovy */
			Binding binding = GroovyUtils.createBuilder( workflow, context );		
			
			/* execution */
			GroovyUtils.execute( binding, transition.getActions() );

		} else {
			if ( log.isDebugEnabled() ) {
				log.debug( "No Grovy Script defined for transition " + transition.toString() );
			}
		}
		
		/* final state */
		State state = changeState( transition, context );
	
		if ( log.isDebugEnabled() ) {
			log.debug( "Final state for WorkItem '" + context.getItem() + "' is '" + state.getName() + "'" );
		}
		
		return state;
	}
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.automata.WorkflowManager#fire(it.inspired.automata.WorkflowContext)
	 */
	public State submit(Workflow workFlow, WorkflowContext context) throws NoTransitionAllowed {
		/* Get the current state */
		State state = workFlow.getCurrentState( context.getItem() );
		
		/* Iterate over all transition */
		Iterator<Transition> iterator = state.getTransitions().iterator();
		
		while ( iterator.hasNext() ) {
			Transition transition = iterator.next();
			if ( evaluateCondition( transition,  context ) ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "Firing transition " + transition.getName() + " from workflow " + workFlow.getName() );
				}
				return fire( transition, context );
			}
		}
		throw new NoTransitionAllowed( "No transition allowed for state " + state.getName() + " in workflow " + workFlow.getName() );
	}
	
	//--------------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see it.inspired.fsm.WorkflowManager#evaluateCondition(it.inspired.fsm.Transition, it.inspired.fsm.WorkflowContext)
	 */
	public boolean evaluateCondition(Transition transition, WorkflowContext context) {
		boolean result = true;
		
		if ( transition.getCondition() != null ) {
			/* Recover the workflow definition from the state of the transition */
			Workflow workflow = transition.getState().getWorkflow();
			
			/* Create binding for glroovy */
			Binding binding = GroovyUtils.createBuilder( workflow, context );
			
			/* execution */
			result = GroovyUtils.evaluate(  binding, transition.getCondition() );
		
		}
		return result;
	}
	
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Change the state of the item populating the history item if required
	 * @param transition The transition to the new state
	 * @param context The workflow context
	 * @return The new state
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected State changeState(Transition transition, WorkflowContext context ) {
		WorkItem item = context.getItem();
		
		if ( item instanceof ExtendedWorkItem ) {
			ExtendedWorkItem extItem = (ExtendedWorkItem) item;
			
			/* Generate a new history item */
			HistoryItem historyItem = newHistoryItem( extItem );
			extItem.getHistories().add( historyItem );
			context.setHistoryItem(historyItem);
			
			/* Update the history item */
			historyItem.setState( extItem.getState() );
			historyItem.setStartTime( extItem.getStateTime() );
			historyItem.setEndTime( new Date() );

			extItem.setStateTime( new Date( historyItem.getEndTime().getTime() + 1 ) );
		}
		
		item.setState( transition.getTo() );
		return transition.getState().getWorkflow().getState( item );
	}
	
	/**
	 * Create a new instance of an history item for a workflow item
	 * @param item The workflow item
	 * @return The new History Item
	 */
	@SuppressWarnings("unchecked")
	protected <E extends HistoryItem> E newHistoryItem( ExtendedWorkItem<E> item ) {
		Type type = 
				((ParameterizedType) item.getClass().getGenericInterfaces()[0])
				.getActualTypeArguments()[0];
		
			try {
				return (E) ((Class<?>) type).newInstance();
			} catch (InstantiationException e) {
				log.error("Error creating history item", e);
			} catch (IllegalAccessException e) {
				log.error("Error creating history item", e);
			}
		
		return null;
	}
	
}
