package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.IDatabase;

public class Photographer extends Account {

	private int photographerID;
	private String companyName;
	private String bankAccount;
	private Boolean active;
	private static IDatabase db;

	/**
	 * Constructor
	 *
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
	 * Authenticate a photographer
	 * @param username
	 * @param password
	 * @return
	 */
	
	public static Photographer authenticate(String username, String password)
	{
		Account a = Account.authenticate(username, password);
		if(a == null)
		{
			return null;
		}
		db = controllers.DatabaseController.getInstance();
		ResultSet rs = db.select("select * from Photographer where accountID = '"+a.getAccountID()+"'");
		
		try {
			if(!rs.next())
			{
				return null;
			}
			else
			{
				try{
					boolean isActive = false;
					if(rs.getInt("isActive") == 1)
					{
						isActive = true;
					}
					
					
					Photographer p = new Photographer(a.getUsername(),a.getName(),a.getAddress(),a.getZipcode(),a.getCity(),a.getEmail(),a.getTelephone(),rs.getString("companyName"),rs.getString("bankAccount"),isActive);
					return p;
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				return null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Search photographer AccountID
	 * 
	 * @param username
	 * @return accountID or -1 if not found
	 */
	public void editPhotographer() {
		try {
			db = controllers.DatabaseController.getInstance();
			ResultSet rs = db.select("SELECT accountID "
					+ "FROM Account "
					+ "WHERE username='"
					+ getUsername() + "'");
			rs.next();
			db.update("UPDATE Account JOIN Photographer "
					+ "ON Account.accountID = Photographer.accountID "
					+ "SET name ='"+ this.getName() 
					+ "', companyname ='"+ this.getCompanyName() 
					+ "', isActive =" + this.active 
					+ ", address ='" + this.getAddress() 
					+ "', zipcode ='" + this.getZipcode() 
					+ "', city ='" + this.getCity() 
					+ "', email ='"+ this.getEmail() 
					+ "', telephone ='"+ this.getTelephone()
					+ "' WHERE Photographer.accountID = " + rs.getInt("accountID") + "");
//			db.update("UPDATE Photographer SET companyname = "+ this.companyName +", isActive = " + this.active 
//					+ " WHERE Photographer.accountID = "
//					+ rs.getInt("accountID") + "");
//			db.update("UPDATE Account SET name ="+ this.getName() 
//					+ ", address ="+ this.getAddress() 
//					+ ", zipcode ="+ this.getZipcode() 
//					+ ", city ="+ this.getCity() 
//					+ ", email ="+ this.getEmail() 
//					+ ", telephone ="+ this.getTelephone() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
