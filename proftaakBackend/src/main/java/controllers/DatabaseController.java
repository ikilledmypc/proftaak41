/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import interfaces.IDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class DatabaseController implements IDatabase{

	private ResultSet result;
	private Statement statement;
	private String host;
	private String userName;
	private String passWord;
	private Connection con;
	private static DatabaseController instance ;

	/**
	 * Constructor
	 */
	protected DatabaseController() {

		
		try {
			Properties rootprops = new Properties();
			rootprops.load(new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"config"+File.separator+"application.properties")));
			this.host = rootprops.getProperty("DBhost");
			this.userName = rootprops.getProperty("DBusername");
			this.passWord = rootprops.getProperty("DBpassword");
			System.out.println("connecting to DB with: "+this.userName+" "+this.passWord);
		} catch (IOException e) {
			this.host = "127.0.0.1";
			this.userName ="root";
			this.passWord = "";
		}
	}

	/**
	 * Connection
	 * @throws SQLException 
	 */
	public boolean connect() throws SQLException {
		if(con==null || con.isClosed()){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://"+this.host+"/pt4?" +"user="+this.userName+"&password="+this.passWord);
			return true;
		} catch (Exception ex) {
			
			System.out
					.println("Connection problem : Can't connect to database");
			ex.printStackTrace();
			return false;
		}
		}
		return true;

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
			if(connect()){
				System.out.println(s);
			statement = con.createStatement();
			result = statement.executeQuery(s);
			//closeConnection();
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		
		return result;
	}

	@Override
	public ResultSet insert(String s) {
		try {
			if(connect()){
			System.out.println(s);
			statement = con.createStatement();			
			statement.executeUpdate(s,Statement.RETURN_GENERATED_KEYS);
			result = statement.getGeneratedKeys();
			//closeConnection();
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
			return null;
		}
		
		return result;
	}

	@Override
	public boolean delete(String s) {
		try {
			if(connect()){
			statement = con.createStatement();
			statement.executeUpdate(s);
			closeConnection();
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(String s) {
		try {
			if (connect()) {
				statement = con.createStatement();
				statement.executeUpdate(s);
				closeConnection();
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseController.class.getName()).log(
					Level.SEVERE, null, ex);
			return false;
		}
		return true;
	}
	
	
}
