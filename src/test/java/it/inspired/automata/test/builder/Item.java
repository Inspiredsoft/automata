package it.inspired.automata.test.builder;

import it.inspired.automata.model.WorkItem;

public class Item implements WorkItem {
	String state;

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}
}
