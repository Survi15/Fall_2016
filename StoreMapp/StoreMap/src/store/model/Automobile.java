package store.model;

public class Automobile extends Products{
	protected String size;
	protected String brandName;
	protected String color;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Automobile(int itemId, double salePrice, String name, String stock, int aisleId, String size, String brandName,
			String color, Category category) {
		super(itemId, salePrice, name, stock, aisleId , category);
		this.size = size;
		this.brandName = brandName;
		this.color = color;
	}
	
	
	

}
