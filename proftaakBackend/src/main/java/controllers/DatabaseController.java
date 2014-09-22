/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import interfaces.IDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class DatabaseController implements IDatabase {

	private ResultSet result;
	private Statement statement;
	private final String host;
	private final String userName;
	private final String passWord;
	private Connection con;
	private static DatabaseController instance = null;

	/**
	 * Constructor
	 */
	protected DatabaseController() {
		host = "";
		userName = "";
		passWord = "";
	}

	/**
	 * Connection
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(host, userName, passWord);
		} catch (Exception ex) {
			System.out
					.println("Connection problem : Can't connect to database");
			ex.printStackTrace();
		}

	}

	/**
	 * close connection
	 */
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * create instance
	 * @return instance
	 */
	public static DatabaseController getInstance() {
		if (instance == null) {
			instance = new DatabaseController();
		}
		return instance;
	}

	@Override
	public ResultSet select(String s) {
		try {
			statement = con.createStatement();
			connect();
			result = statement.executeQuery(s);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		closeConnection();
		return result;
	}

	@Override
	public boolean insert(String s) {
		try {
			statement = con.createStatement();
			connect();
			statement.executeUpdate(s);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
			return false;
		}
		closeConnection();
		return true;
	}

	@Override
	public boolean delete(String s) {
		try {
			statement = con.createStatement();
			connect();
			statement.executeUpdate(s);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
			return false;
		}
		closeConnection();
		return true;
	}
}
