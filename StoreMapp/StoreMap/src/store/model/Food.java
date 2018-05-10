package store.model;

public class Food extends Products{
	protected String brandName;
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Food(int itemId, double salePrice, String name, String stock, int aisleId, String brandName, Category category) {
		super(itemId, salePrice, name, stock, aisleId , category);
		this.brandName = brandName;
	}
	
	

}
