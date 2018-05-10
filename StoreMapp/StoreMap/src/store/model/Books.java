package store.model;

public class Books extends Products{
	protected String author;
	protected String publisher;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Books(int itemId, double salePrice, String name, String stock, int aisleId, String author, String publisher, Category category) {
		super(itemId, salePrice, name, stock, aisleId, category);
		this.author = author;
		this.publisher = publisher;
	}
	
	
	
	

}
