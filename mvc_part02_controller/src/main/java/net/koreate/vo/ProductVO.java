package net.koreate.vo;

public class ProductVO {
	private String name;
	private int price;
	
	public ProductVO() {
		System.out.println("Product 기본 생성자 호출");
	}

	public ProductVO(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
	
	
}
