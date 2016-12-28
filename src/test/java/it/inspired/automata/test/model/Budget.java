package it.inspired.automata.test.model;

import java.math.BigDecimal;

public class Budget {
	private BigDecimal value;
	
	public Budget(BigDecimal value) {
		super();
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
