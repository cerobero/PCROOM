package ui.client;

public class Food {
	private String name;
	private int price;
	private String maker;
	private int count;
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String toString(){// \t =>����������ȿ�� ������ �����ؿ�
		return ""+getName()+"\t  "+getMaker()+"\t  "+getCount()+"��"+"\t"+getPrice()+"��";
		
	}

}
