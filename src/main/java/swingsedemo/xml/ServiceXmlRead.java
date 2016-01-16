package swingsedemo.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class ServiceXmlRead {

	public void readXml(File file) throws JAXBException{
		//File file = new File( "list_countries.xml" );
        JAXBContext jaxbContext = JAXBContext.newInstance( BooksList.class );
        /**
         * the only difference with the marshaling operation is here
         */
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        BooksList booksList = (BooksList)jaxbUnmarshaller.unmarshal( file );
        System.out.println( booksList );
	}
}
