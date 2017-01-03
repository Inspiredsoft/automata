package it.inspired.automata.test.po.model;

import java.util.Date;

import it.inspired.automata.model.HistoryItem;

public class OrderHistory implements HistoryItem {

	private String 	state;
	private Date 	startTime;
	private Date 	endTime;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return this.state + " from " + startTime + " to " + endTime;
	}

}
