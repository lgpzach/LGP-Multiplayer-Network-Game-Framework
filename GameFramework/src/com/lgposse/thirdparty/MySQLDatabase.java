package com.lgposse.thirdparty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// FIXME: MAKE A WEB-BASED DATABASE!
public class MySQLDatabase {
	protected Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	protected String hostname;
	protected int port;
	protected String database;
	protected String username;
	protected String password;
	
	public int openConnections;
	
	public MySQLDatabase(String hostname, int port, String database, String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		openConnections = 0;
	}
	
	public ResultSet getAll(String table) {
		return this.getQuery("select * from " + database + "." + table);
	}
	
	public ResultSet getOne(String table, String select, String where) {
		return this.getQuery("select " + select + " from " + database + "." + table
				+ " where " + where + " limit 1");
	}
	
	public ResultSet getRows(String table, String select, String where) {
		return this.getQuery("select " + select + " from " + database + "." + table
					+ " where " + where);
	}
	
	public ResultSet getRows(String table, String select) {
		return this.getQuery("select " + select + " from " + database + "." + table);
	}
	
	public void execute(String q) {
		try {
			this.connect();
			statement = connection.createStatement();
			statement.executeQuery(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void execute(PreparedStatement s) {
		try {
			this.connect();
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected ResultSet getQuery(String q) {
		try {
			//this.connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(q);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void truncate(String table) {
		this.execute("truncate table " + table);
	}
	
	protected void connect() throws SQLException {
		String host = "jdbc:mysql://" + hostname + ":" + Integer.toString(port) + "/" + database + "?user=" + username + "&password=" + password;
		//System.out.println(host);
		System.out.println("Opening...");
		openConnections++;
		connection = DriverManager.getConnection(host);
	}
	
	protected void close() {
		System.out.println("Closing...");
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
			System.out.println(connection.isClosed());
		} catch (Exception e) {}
	}
}
