package domain;

import java.sql.ResultSet;

import interfaces.IDatabase;

public class Account {

	private int accountID;
	private String username;
	private String password;
	private String name;
	private String address;
	private String zipcode;
	private String city;
	private String email;
	private int telephone;
	private static IDatabase db;

	/**
	 * Constructor
	 * 
	 * @param accountID
	 * @param username
	 * @param name
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param email
	 * @param telephone
	 */
	public Account(int accountID, String username, String name, String address,
			String zipcode, String city, String email, int telephone) {
		super();
		this.accountID = accountID;
		this.username = username;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.email = email;
		this.telephone = telephone;
	}

	/**
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public void register() {
		db = null;
		db.insert("insert into account (username,password,name,address,zipcode,city,email,telephone) values ("
				+ this.username
				+ ","
				+ this.password
				+ ","
				+ this.name
				+ ","
				+ this.address
				+ ","
				+ this.zipcode
				+ ","
				+ this.city
				+ ","
				+ this.email + "," + this.telephone + ")");

	}

	public static Account authenticate(String username, String password) {
		// TODO GET DATABASE INSTANCE AND RETRIVE USER
		db = null;
		ResultSet rs = db.select("select * from account where username ="
				+ username + "and password=" + password);
		if (rs == null) {
			return null;
		} else {

			return null;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}