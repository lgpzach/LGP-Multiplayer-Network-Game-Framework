package com.lgposse.cards.tests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lgposse.cards.models.CardGame;

class MySQLTest {

	public static void main(String[] args) throws Exception {
		MySQLTest dao = new MySQLTest();
		dao.readDataBase();
	}

	private Connection connect = null;
	private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://instance7519.db.xeround.com:5720/cards?"
							+ "user=lgpadmin&password=rootbeer");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from CARDS.GAMES");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
//			preparedStatement = connect
//					.prepareStatement("insert into  FEEDBACK.COMMENTS values (default, ?, ?, ?, ? , ?, ?)");
//			// "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
//			// Parameters start with 1
//			preparedStatement.setString(1, "Test");
//			preparedStatement.setString(2, "TestEmail");
//			preparedStatement.setString(3, "TestWebpage");
//			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//			preparedStatement.setString(5, "TestSummary");
//			preparedStatement.setString(6, "TestComment");
//			preparedStatement.executeUpdate();
//
//			preparedStatement = connect
//					.prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
//			resultSet = preparedStatement.executeQuery();
//			writeResultSet(resultSet);
//
//			// Remove again the insert comment
//			preparedStatement = connect
//			.prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
//			preparedStatement.setString(1, "Test");
//			preparedStatement.executeUpdate();
//			
//			resultSet = statement
//			.executeQuery("select * from FEEDBACK.COMMENTS");
//			writeMetaData(resultSet);
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

//	private void writeMetaData(ResultSet resultSet) throws SQLException {
//		// 	Now get some metadata from the database
//		// Result set get the result of the SQL query
//		
//		System.out.println("The columns in the table are: ");
//		
//		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
//		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
//			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
//		}
//	}

	private void writeResultSet(ResultSet resultSet) throws SQLException, IOException, ClassNotFoundException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			ObjectInputStream gamedata = new ObjectInputStream(resultSet.getBlob("data").getBinaryStream());
			CardGame g = (CardGame) gamedata.readObject();
			System.out.println("Game ID: " + id);
			System.out.println("Game name: " + name);
			System.out.println(g);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}