package com.example.jdbcdemo.domain;

public class Order {
	
	private long id;
	
	private String customer;
	private long idPick;
	private int amount;
	
	public Order() {
	}
	
	public Order(String customer, long idPick, int amount) {
		super();
		this.customer = customer;
		this.idPick = idPick;
		this.amount = amount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public long getIdPick() {
		return idPick;
	}
	public void setIdPick(long idPick) {
		this.idPick = idPick;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
