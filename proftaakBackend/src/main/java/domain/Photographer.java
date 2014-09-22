package domain;

public class Photographer {

	private int photographerID;
	private String companyName;
	private String bankAccount;
	private Boolean active;

	/**
	 * Constructor
	 * 
	 * @param companyName
	 * @param bankAccount
	 * @param active
	 */
	public Photographer(String companyName, String bankAccount, Boolean active) {
		super();
		this.companyName = companyName;
		this.bankAccount = bankAccount;
		this.active = active;
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

}
