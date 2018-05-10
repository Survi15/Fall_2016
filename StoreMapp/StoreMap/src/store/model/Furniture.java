package store.model;

public class Furniture extends Products{
	protected String brandName;
	protected String color;
	protected String size;
	
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Furniture(int itemId, double salePrice, String name, String stock, int aisleId, String brandName, String color,
			String size , Category category) {
		super(itemId, salePrice, name, stock, aisleId , category);
		this.brandName = brandName;
		this.color = color;
		this.size = size;
	}
	
	

}
