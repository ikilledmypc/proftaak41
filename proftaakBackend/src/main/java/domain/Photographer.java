package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			String zipcode, String city, String email, int telephone,
			String companyName, String bankAccount) {
		super(username, name, address, zipcode, city, email, telephone);
		this.companyName = companyName;
		this.bankAccount = bankAccount;
		this.active = false;
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

	/**
	 * Enable or Disable photographer account
	 * 
	 * @param photographerID
	 */
	public void enableDisablePhotographer(int photographerID) {
		db = controllers.DatabaseController.getInstance();
		rs = db.select("SELECT isActive FROM Photographer"
				+ " WHERE accountID='" + photographerID);

		try {
			if (rs.getBoolean("isActive") == true) {
				db.update("UPDATE Photographer " + "SET isActive = '0' "
						+ "WHERE Photographer.accountID ='" + photographerID);
			} else {
				db.update("UPDATE Photographer " + "SET isActive = '1' "
						+ "WHERE Photographer.accountID ='" + photographerID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search photographer AccountID
	 * 
	 * @param username
	 * @return accountID or -1 if not found
	 */
	public int searchPhotographerID(String username) {
		db = controllers.DatabaseController.getInstance();
		rs = db.select("SELECT accountID FROM Account WHERE username='"
				+ username);

		try {
			return rs.getInt("accountID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
