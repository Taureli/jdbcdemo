package com.example.jdbcdemo.domain;

public class Zamowienie {
	
	private int id;
	
	private String customer;
	private int idPick;
	private int amount;
	
	public Zamowienie() {
	}
	
	public Zamowienie(String customer, int amount) {
		super();
		this.customer = customer;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getIdPick() {
		return idPick;
	}
	public void setIdPick(int idPick) {
		this.idPick = idPick;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
