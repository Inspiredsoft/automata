package it.inspired.fsm.test;

import java.math.BigDecimal;

import it.inspired.fsm.State;
import it.inspired.fsm.Transition;
import it.inspired.fsm.Workflow;
import it.inspired.fsm.WorkflowContext;
import it.inspired.fsm.WorkflowManager;
import it.inspired.fsm.WorkflowManagerImpl;

public class MainWorkflow {

	public static void main(String[] args) throws Exception {
		
		WorkflowManager workflowManager = new WorkflowManagerImpl( );
		Workflow poWorkFlow = workflowManager.getWorkflow( "purchaseOrder" );
		
		Order order = new Order("MacBook Pro");
		
		WorkflowContext workFlowContext = new WorkflowContext( order );
		poWorkFlow.start( order );
        //workFlowContext.setAttribute( "quietanza", quietanza );
       
        State state = poWorkFlow.getCurrentState( order );
        System.out.println("Allowed transition from state " + state.getName() + ": " + state.getTransitions() );
        
        Transition transition = state.getTransition( "approve"  );
        workflowManager.fire( transition, workFlowContext );
        
        System.out.println( order );
        
        state = poWorkFlow.getCurrentState( order );
        System.out.println("Allowed transition from state " + state.getName() + ": " + state.getTransitions() );
        
        workFlowContext.setAttribute( "invoice", new Invoice( order, BigDecimal.TEN ) );
        transition = state.getTransition( "process"  );
        workflowManager.fire( transition, workFlowContext );
        
        System.out.println( order );
	}
}
