package domain;

import java.util.ArrayList;
import java.util.Calendar;import java.util.Locale;
;

public class Order {

	private int orderID;
	private Calendar date;
	private int accountID;
	private String status;
	private ArrayList<Product> products;

	/**
	 * Constructor
	 * 
	 * @param date
	 * @param accountID
	 */
	public Order(Calendar date, int accountID) {
		super();
		this.date = date;
		this.accountID = accountID;
		products = new ArrayList<Product>();
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
        
        @Override
        public String toString()
        {
            return this.date.get(Calendar.DAY_OF_WEEK) + "-" + this.date.get(Calendar.MONTH) + "-" + this.date.get(Calendar.YEAR);
        }

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Calendar date) {
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
	
	public void addProduct(Product p)
	{
		products.add(p);	
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
        
        public ArrayList<Product> getProducts()
        {
            return products;
        }
}
