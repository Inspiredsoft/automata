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

package it.inspired.automata.test.po;

import it.inspired.automata.ClasspathWorkflowManager;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.model.State;
import it.inspired.automata.model.Transition;
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.test.po.model.Budget;
import it.inspired.automata.test.po.model.Invoice;
import it.inspired.automata.test.po.model.Order;
import it.inspired.automata.test.po.model.OrderHistory;

import java.math.BigDecimal;

public class PurchaseOrderWithInvoice {
	
	/* Change value to test different condition */
	private static final BigDecimal budget = new BigDecimal("50");

	public static void main(String[] args) throws Exception {
		
		/**
		 * Generate a new istance of workflow manager and read all the
		 * fsm.xml file in the classpath
		 */
		WorkflowManager workflowManager = new ClasspathWorkflowManager();
		
		/**
		 * Get the purchase order workflow definition
		 */
		Workflow poWorkFlow = workflowManager.getWorkflow( "purchaseOrder" );
		
		/**
		 * Generate an new order for a macBook Pro computer
		 */
		Order order = new Order("MacBook Pro");
		
		/**
		 * Create a workflow context for the order and sets the starting state
		 */
		WorkflowContext workFlowContext = new WorkflowContext( order );
		poWorkFlow.start( order );
		
        
		/**
		 * Get and print the current state ad the allowed transition
		 */
        State state = poWorkFlow.getCurrentState( order );
        System.out.println("Transitions from state " + state.getName() + ": " + state.getTransitions() );
        
        Thread.sleep( 5000 );

        /**
         * Retrieve the transition named "approve" and check if is allowed
         */
        Transition transition = state.getTransition( "approve"  );
        workFlowContext.setAttribute( "budget", new Budget( budget ) );
        boolean allowed = workflowManager.evaluateCondition( transition,  workFlowContext );
        System.out.println( "Transition " + transition.getName() + " is " + (allowed ? "allowed" : "NOT allowed")  );
        
        /**
         * Approve the transition anyway since the condition is not checked during fire
         */
        state = workflowManager.fire( transition, workFlowContext );
        System.out.println( order );
        
        Thread.sleep( 5000 );
        
        /**
         * Print the state after the transition
         */
        System.out.println("Transition from state " + state.getName() + ": " + state.getTransitions() );
        
        /**
         * Create an invoce for the item, then process the order firing the transition name "process"
         */
        workFlowContext.setAttribute( "invoice", new Invoice( order, BigDecimal.TEN ) );
        state = workflowManager.fire( state, "process", workFlowContext );
        System.out.println( order );
        
        /**
         * Print the history
         */
        for ( OrderHistory history : order.getHistories() ) {
        	System.out.println( history );
        }
	}
}
