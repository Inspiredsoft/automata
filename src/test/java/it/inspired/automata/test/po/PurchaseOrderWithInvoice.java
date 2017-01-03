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

import java.math.BigDecimal;

public class PurchaseOrderWithInvoice {

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
        System.out.println("Transition from state " + state.getName() + ": " + state.getTransitions() );
        

        /**
         * Retrieve the transition named "approve" and check if is allowed
         */
        Transition transition = state.getTransition( "approve"  );
        workFlowContext.setAttribute( "budget", new Budget( new BigDecimal("50") ) );
        boolean allowed = workflowManager.evaluateCondition( transition,  workFlowContext );
        System.out.println( "Transition " + transition.getName() + " is " + (allowed ? "allowed" : "NOT allowed")  );
        
        /**
         * Approve the transition anyway since the condition is not checked during fire
         */
        workflowManager.fire( transition, workFlowContext );
        System.out.println( order );
        
        /**
         * Recover the state after the transition
         */
        state = poWorkFlow.getCurrentState( order );
        System.out.println("Transition from state " + state.getName() + ": " + state.getTransitions() );
        
        /**
         * Create an invoce for the item, then process the order firing the transition name "process"
         */
        workFlowContext.setAttribute( "invoice", new Invoice( order, BigDecimal.TEN ) );
        workflowManager.fire( state, "process", workFlowContext );
        System.out.println( order );
	}
}
