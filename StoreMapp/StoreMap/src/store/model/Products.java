package store.model;


public class Products {
	protected int itemId;
	protected  double salePrice;
	protected String name;
	protected String stock;
	protected int aisleId;
	protected Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public enum Category {
		FOOD, ELECTRONICS, FURNITURE, APPAREL, AUTOMOBILEPARTS, PERSONALCARE,BOOKS
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getAisleId() {
		return aisleId;
	}
	public void setAisleId(int aisleId) {
		this.aisleId = aisleId;
	}
	public Products(int itemId, double salePrice, String name, String stock, int aisleId , Category category) {
		super();
		this.itemId = itemId;
		this.salePrice = salePrice;
		this.name = name;
		this.stock = stock;
		this.aisleId = aisleId;
		this.category = category;
	}
	

}
