package edu.kh.jsp.model.dto;

public class Pizza {
	private String name; // 피자 이름
	private int price; // 가격
	
	public Pizza() {}
	public Pizza(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }
}
