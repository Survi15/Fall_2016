package store.model;

public class Electronics extends Products {
	protected String modelNumber;
	protected String color;
	protected String brandName;
	
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNo) {
		this.modelNumber = modelNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Electronics(int itemId, double salePrice, String name, String stock, int aisleId, String modelNo, String color, Category category, String brandName) {
		super(itemId, salePrice, name, stock, aisleId,category);
		this.modelNumber = modelNo;
		this.color = color;
		this.brandName =brandName;
	}
	
	
	

}
