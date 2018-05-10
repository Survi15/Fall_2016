package store.model;

public class PersonalCare extends Products {
	protected String brandName;
	protected String size;
	protected String color;
	protected String gender;
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PersonalCare(int itemId, double salePrice, String name, String stock, int aisleId, String brandName, String size,
			String color, String gender , Category category) {
		super(itemId, salePrice, name, stock, aisleId, category);
		this.brandName = brandName;
		this.size = size;
		this.color = color;
		this.gender = gender;
	}
	

	
	
}
