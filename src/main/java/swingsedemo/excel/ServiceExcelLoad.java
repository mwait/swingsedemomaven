package swingsedemo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ServiceExcelLoad {

	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}
	
	public List<Book> readBooksFromExcelFile(String excelFilePath) throws IOException {
	    List<Book> listBooks = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Book aBook = new Book();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	            //przechodzę po 3 pierwszych kolumnach
	            switch (columnIndex) {
	            case 0:
	                aBook.setTitle((String) getCellValue(nextCell));
	                break;
	            case 1:
	                aBook.setAuthor((String) getCellValue(nextCell));
	                break;
	            case 2:
	                aBook.setPrice((double) getCellValue(nextCell));
	                break;
	            }
	        }
	        listBooks.add(aBook);
	    }
	    workbook.close();
	    inputStream.close();
	 
	    return listBooks;
	}
	
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
}


/*
Get a specific sheet:
Sheet thirdSheet = workbook.getSheetAt(2);

Get sheet name:
String sheetName = sheet.getSheetName();

Get total number of sheets in the workbook:
int numberOfSheets = workbook.getNumberOfSheets();

Get all sheet names in the workbook:
int numberOfSheets = workbook.getNumberOfSheets();
 
for (int i = 0; i < numberOfSheets; i++) {
    Sheet aSheet = workbook.getSheetAt(i);
    System.out.println(aSheet.getSheetName());
}

Get comment of a specific cell:
Comment cellComment = sheet.getCellComment(2, 2);
System.out.println("comment: " + cellComment.getString());*/
