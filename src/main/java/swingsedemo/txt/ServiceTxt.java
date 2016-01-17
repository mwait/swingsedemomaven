package swingsedemo.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import swingsedemo.xml.BookXml;

public class ServiceTxt {
	private static final String fileTxtDestination ="fileFromSaveBookToFileService.txt" ;
	
	public void saveBookToFile (List<BookXml> books) throws FileNotFoundException, UnsupportedEncodingException{
		
		PrintWriter printWriter = new PrintWriter(fileTxtDestination, "UTF-8"); //Windows-1250
		for(BookXml b: books){
			printWriter.println(b);
		}
		printWriter.close();
	}
	
	public void loadBookToFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileTxtDestination)))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//apachowa metoda na zaczytanie pliku do Stringa
		String allContentToFile=null;
		try {
			allContentToFile=FileUtils.readFileToString(new File(fileTxtDestination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(allContentToFile);
	}
}
