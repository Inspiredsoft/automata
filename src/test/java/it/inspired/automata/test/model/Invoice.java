package it.inspired.automata.test.model;

import java.math.BigDecimal;

public class Invoice {
	private Order order;		// Order in the invoice
	private BigDecimal total;	// Invoice total
	
	public Invoice(Order order, BigDecimal total) {
		super();
		this.order = order;
		this.total = total;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
}
