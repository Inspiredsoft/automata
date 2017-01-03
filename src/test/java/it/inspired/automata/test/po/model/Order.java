package it.inspired.automata.test.po.model;

import it.inspired.automata.model.WorkItem;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A pUrchase Order
 * @author Massimo Romano
 *
 */
public class Order implements WorkItem {
	private String item;			// Item to order
	private Integer number;			// Approval number
	private String note;			// Note
	private Date approvalDate;		// Approval date
	private Date cancelDate;		// Cancel date
	private Date processDate;		// Procedd date
	private String state;			// State of the order
	private BigDecimal paid;		// Total paid for the order
	
	public Order(){}
	public Order(String item){
		this.item = item;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public BigDecimal getPaid() {
		return paid;
	}
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder( "Order for " + item );
		if ( state.equals( "APPROVED") ) {
			str.append( " approved in date " + this.approvalDate + " with number " + this.number );
		
		} else if ( state.equals( "DENIED") ) {
			str.append( " denied in date " + this.cancelDate );
		
		} else if ( state.equals( "CANCELLED") ) {
			str.append( " cancelled in date " + this.cancelDate );
		
		} else if ( state.equals( "PROCESSED") ) {
			str.append( " processed in date " + this.processDate + " for " + this.paid + " euros" );
		} 
		return str.toString();
	}
}
