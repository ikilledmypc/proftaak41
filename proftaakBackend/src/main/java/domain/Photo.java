package domain;

import java.util.Calendar;
import java.util.Date;

public class Photo {

	private int photoID;
	private Date uploadDate;
	private float price;

	/**
	 * Constructor
	 * 
	 * @param uploadDate
	 * @param price
	 */
	public Photo(Date uploadDate, float price) {
		super();
		this.uploadDate = uploadDate;
		this.price = price;
	}
	
	public Photo(int id,Date uploadDate, float price) {
		super();
		this.uploadDate = uploadDate;
		this.price = price;
		this.photoID = id;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate
	 *            the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the photoID
	 */
	public int getPhotoID() {
		return photoID;
	}

	/**
	 * @param photoID
	 *            the photoID to set
	 */
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
}
