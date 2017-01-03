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
import it.inspired.automata.model.Workflow;
import it.inspired.automata.model.WorkflowContext;
import it.inspired.automata.test.po.model.Budget;
import it.inspired.automata.test.po.model.Order;

import java.math.BigDecimal;

public class PurchaseOrderWithBudget {

	/* Change value to test different condition */
	private static final BigDecimal budget = new BigDecimal("5");
	
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
		
		Thread.sleep( 2000 );
		
		/**
		 * Adding budget to the context
		 */
		workFlowContext.setAttribute( "budget", new Budget( budget ) );
		
		workflowManager.submit( poWorkFlow, workFlowContext );
		
		System.out.println( order );
		System.out.println( order.getHistories().get(0) );
	}

}
