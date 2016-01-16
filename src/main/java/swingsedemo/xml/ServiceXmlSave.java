package swingsedemo.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class ServiceXmlSave {

	public void generateXmlFile(List<BookXml> bookXml) throws JAXBException {
		
		BooksList booksList = new BooksList();
		for(BookXml books: bookXml){
			booksList.add(books);
		}
      

        /* init jaxb marshaler */
        JAXBContext jaxbContext = JAXBContext.newInstance( BooksList.class );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        /* set this flag to true to format the output */
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

        /* marshaling of java objects in xml (output to file and standard output) */
        jaxbMarshaller.marshal( booksList, new File( "list_books.xml" ) );
        jaxbMarshaller.marshal( booksList, System.out );
		
	}
	
	public List<BookXml> books () {
		List<BookXml> list = new ArrayList<BookXml>();
		list.add(new BookXml("Tytuł 1", "Author 1", 25.22f));
		list.add(new BookXml("Tytuł 2", "Author 2", 29.22f));
		return list;
	}
}
