package domain;

import java.util.Calendar;
import java.util.Date;

public class Photo {

	private int photoID;
	private String name;
	private Calendar uploadDate;
	private float price;
	private int height;
	private int width;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param uploadDate
	 * @param price
	 * @param height
	 * @param width
	 */
	public Photo(String name, Calendar uploadDate, float price, int height, int width) {
		super();
		this.name = name;
		this.uploadDate = uploadDate;
		this.price = price;
		this.height = height;
		this.width = width;
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
	public Calendar getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate
	 *            the uploadDate to set
	 */
	public void setUploadDate(Calendar uploadDate) {
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
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
        
        /**
	 * @return the width
	 */
	public int getwidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setwidth(int width) {
		this.width = width;
	}
}
