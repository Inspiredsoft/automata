package it.inspired.automata.test;

import java.math.BigDecimal;

import it.inspired.automata.Workflow;
import it.inspired.automata.WorkflowContext;
import it.inspired.automata.WorkflowManager;
import it.inspired.automata.WorkflowManagerImpl;
import it.inspired.automata.test.model.Budget;
import it.inspired.automata.test.model.Order;

public class PurchaseOrderWithBudget {

	public static void main(String[] args) throws Exception {
		/**
		 * Generate a new istance of workflow manager and read all the
		 * fsm.xml file in the classpath
		 */
		WorkflowManager workflowManager = new WorkflowManagerImpl();
		
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
		 * Adding budget to the context
		 */
		workFlowContext.setAttribute( "budget", new Budget( new BigDecimal("5") ) );
		
		workflowManager.fire( poWorkFlow, workFlowContext );
		
		System.out.println( order );
	}

}
