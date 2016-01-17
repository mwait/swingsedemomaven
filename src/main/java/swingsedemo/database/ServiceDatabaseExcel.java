package swingsedemo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import swingsedemo.excel.Book;
import swingsedemo.excel.ServiceExcelSave;

public class ServiceDatabaseExcel {

	private static final String url="jdbc:mysql://localhost:3306/";
	private Connection con;
	private Statement statement;
	public ServiceDatabaseExcel () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			con=DriverManager.getConnection(url, "", "");
			statement = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addDatabase() throws SQLException{
		
		statement.executeUpdate("DROP TABLE IF EXISTS `BOOKS`");
		statement.executeUpdate("CREATE TABLE `BOOKS` (title VARCHAR(30), author VARCHAR(40), price VARCHAR(40))");
		addDateToBase();
		System.out.println(getDataFromBase().toString());
	}
	
	public void addDateToBase () throws SQLException{
		ServiceExcelSave ser = new ServiceExcelSave();
		List<Book> listBook = ser.getListBook();
		//pojedyńcze dodanie
		statement.executeUpdate("INSERT INTO BOOKS VALUES (\"Małpa w Kąpieli\",\"Aleksander Fredro\",\"105.00\")");
		//lista
		PreparedStatement ps = con.prepareStatement("INSERT INTO BOOKS VALUES (?,?,?)");
		for(Book b: listBook){
			ps.setString(1, b.getTitle()); ps.setString(2, b.getAuthor()); ps.setString(3, Double.toString(b.getPrice()));
			ps.addBatch();
		}
		ps.executeBatch();
	
	}
	
	public List<Book> getDataFromBase() throws SQLException {
		List<Book> resultList = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKS");
		while (resultSet.next()){
			resultList.add(new Book(resultSet.getString("title"), resultSet.getString("author"), Double.parseDouble(resultSet.getString("price"))));
		}
		resultSet.close();
		return resultList;
	}
	
	public void exitDatabase(){
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
