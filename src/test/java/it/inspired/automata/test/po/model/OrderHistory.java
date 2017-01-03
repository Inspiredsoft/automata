package it.inspired.automata.test.po.model;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm:ss" );
		return this.state + " from " + sdf.format( startTime ) + " to " + sdf.format( endTime );
	}

}
