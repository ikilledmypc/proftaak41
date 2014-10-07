package domain;

import java.util.ArrayList;
import java.util.Date;

public class Order {

	private int orderID;
	private Date date;
	private int accountID;
	private ArrayList<Product> products;

	/**
	 * Constructor
	 * 
	 * @param date
	 * @param accountID
	 */
	public Order(Date date, int accountID) {
		super();
		this.date = date;
		this.accountID = accountID;
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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID
	 *            the orderID to set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}
