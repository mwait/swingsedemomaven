package swingsedemo.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = { "title", "author", "price" } )
@XmlRootElement( name = "Book" )
public class BookXml {

	   private String title;
	    private String author;
	    private double price;
	    
		public BookXml(String title, String author, double price) {
			this.title = title;
			this.author = author;
			this.price = price;
		}
		public BookXml() {
			super();
		}
	    
		
		public String getTitle() {
			return title;
		}
		 @XmlElement( name = "title" )
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuthor() {
			return author;
		}
		 @XmlElement( name = "author" )
		public void setAuthor(String author) {
			this.author = author;
		}
		public double getPrice() {
			return price;
		}
		 @XmlElement( name = "price" )
		public void setPrice(double price) {
			this.price = price;
		}
		@Override
		public String toString() {
			return "BookXml [title=" + title + ", author=" + author + ", price=" + price + "]";
		}
		 
		
	/*	@XmlAttribute(name = "atrybut dla BookXml", required = true)
		public void setImportance(int importance) {
		this.importance = importance;
		}*/

		 
}
