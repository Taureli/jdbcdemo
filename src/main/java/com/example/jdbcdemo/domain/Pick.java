package com.example.jdbcdemo.domain;

public class Pick {
	
	private int id;
	
	private String company;
	private String name;
	private double milimeters;
	private double price;
	
	public Pick() {
	}
	
	public Pick(String company, String name, double milimeters, double price) {
		super();
		this.company = company;
		this.name = name;
		this.milimeters = milimeters;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMilimeters() {
		return milimeters;
	}
	public void setMilimeters(double milimeters) {
		this.milimeters = milimeters;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
