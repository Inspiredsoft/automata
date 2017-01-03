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

package it.inspired.automata.test.po.model;

import it.inspired.automata.model.ExtendedWorkItem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A purchase Order
 * @author Massimo Romano
 *
 */
public class Order implements ExtendedWorkItem<OrderHistory> {
	private String item;			// Item to order
	private Integer number;			// Approval number
	private String note;			// Note
	private Date approvalDate;		// Approval date
	private Date cancelDate;		// Cancel date
	private Date processDate;		// Procedd date
	private BigDecimal paid;		// Total paid for the order
	
	/* Inherited from ExtendedWorkItem */
	private String state;													// State of the order
	private Date stateTime;													// Starting date of the state
	private List<OrderHistory> histories = new ArrayList<OrderHistory>(); 	// The history of the item

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
	
	public BigDecimal getPaid() {
		return paid;
	}
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getStateTime() {
		return stateTime;
	}
	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}
	
	public List<OrderHistory> getHistories() {
		return histories;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm:ss" );
		StringBuilder str = new StringBuilder( "Order for " + item );
		if ( state.equals( "APPROVED") ) {
			str.append( " approved in date " + sdf.format( this.approvalDate ) + " with number " + this.number );
		
		} else if ( state.equals( "DENIED") ) {
			str.append( " denied in date " + sdf.format( this.cancelDate ) );
		
		} else if ( state.equals( "CANCELLED") ) {
			str.append( " cancelled in date " + sdf.format( this.cancelDate ) );
		
		} else if ( state.equals( "PROCESSED") ) {
			str.append( " processed in date " + sdf.format( this.processDate  ) + " for " + this.paid + " euros" );
		} 
		return str.toString();
	}
}
