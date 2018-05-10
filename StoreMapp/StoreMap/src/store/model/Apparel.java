package store.model;

public class Apparel extends Products {
	protected String brandName;
	protected String gender;
	protected String color;
	protected String size;
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Apparel(int itemId, double salePrice, String name, String stock, int aisleId, String brandName, String gender,
			String color , Category category, String size) {
		super(itemId, salePrice, name, stock, aisleId , category);
		this.brandName = brandName;
		this.gender = gender;
		this.color = color;
		this.size = size;
	}
	
	
	

}
