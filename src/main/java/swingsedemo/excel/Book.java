package swingsedemo.excel;

public class Book {
    private String title;
    private String author;
    private double price;
 
  
 
    public Book(String title, String author, double price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

    public Book() {
    	
    }
	public String toString() {
        return String.format("%s - %s - %.2f", title, author, price);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
 
    
    
}