package domain;

import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.IDatabase;

public class Photographer extends Account {

	private int photographerID;
	private String companyName;
	private String bankAccount;
	private Boolean active;
	private static IDatabase db;
	private ArrayList<Photographer> photographers;
	private ResultSet rs;

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
	 * @param companyName
	 * @param bankAccount
	 */
	public Photographer(String username, String name, String address,
			String zipcode, String city, String email, String telephone,
			String companyName, String bankAccount) {
		super(username, name, address, zipcode, city, email, telephone);
		this.companyName = companyName;
		this.bankAccount = bankAccount;
		this.active = false;
	}
	
	/**
	 * Constructor
	 * @param username
	 * @param name
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param email
	 * @param telephone
	 * @param companyName
	 * @param bankAccount
	 * @param isActive
	 */
	public Photographer(String username, String name, String address,
			String zipcode, String city, String email, String telephone,
			String companyName, String bankAccount, boolean isActive) {
		super(username, name, address, zipcode, city, email, telephone);
		this.companyName = companyName;
		this.bankAccount = bankAccount;
		this.active = isActive;
	}

	/**
	 * Constructor
	 *
	 * @param username
	 * @param isActive
	 */
	public Photographer(String username, boolean isActive) {
		super(username);
		this.active = isActive;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount
	 *            the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the photographerID
	 */
	public int getPhotographerID() {
		return photographerID;
	}

	/**
	 * @param photographerID
	 *            the photographerID to set
	 */
	public void setPhotographerID(int photographerID) {
		this.photographerID = photographerID;
	}

	public void registerPhotographer() {
		int accountdbid = super.register();
		if (accountdbid != 0) {
			System.out
					.println("insert into photographer (accountID, companyname, bankaccount, isActive) values ('"
							+ accountdbid
							+ "','"
							+ this.companyName
							+ "','"
							+ this.bankAccount + "','" + 0 + "')");
			db = controllers.DatabaseController.getInstance();
			db.insert("insert into photographer (accountID, companyname, bankaccount, isActive) values ('"
					+ accountdbid
					+ "','"
					+ this.companyName
					+ "','"
					+ this.bankAccount + "','" + 0 + "')");
		}

	}

	/**
	 * Search photographer AccountID
	 * 
	 * @param username
	 * @return accountID or -1 if not found
	 */
	public void enableDisablePhotographer() {
		try {
			System.out.print(getUsername());
			System.out.print(this.active);
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db
					.select("SELECT accountID FROM Account WHERE username='"
							+ getUsername() + "'");
			rs.next();
			System.out.print(rs.getInt("accountID"));
			System.out.print("SELECT accountID FROM Account WHERE username='"
					+ getUsername() + "'");
			db.update("UPDATE Photographer SET isActive = " + this.active
					+ " WHERE Photographer.accountID = "
					+ rs.getInt("accountID") + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
