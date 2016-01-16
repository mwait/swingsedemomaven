package swingsedemo.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "BooksList" )
public class BooksList {

	private List<BookXml> bookXml;

	public List<BookXml> getBookXml() {
		return bookXml;
	}
	
    @XmlElement( name = "Book" )
	public void setBookXml(List<BookXml> bookXml) {
		this.bookXml = bookXml;
	}
	
    public void add( BookXml bookXml )
    {
        if( this.bookXml == null )
        {
            this.bookXml = new ArrayList<BookXml>();
        }
        this.bookXml.add( bookXml );
    }
    
	@Override
	public String toString() {
		StringBuffer st = new StringBuffer();
		for(BookXml b: bookXml){
			st.append(b.toString()+"\n");
		}
		return st.toString();
	}
    
    
	
}
