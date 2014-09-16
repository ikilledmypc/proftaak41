package domain;

import java.util.Date;

public class Photo {
	private int photoID;
	private Date uploadDate;
	private float price;
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
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
	 * @param uploadDate the uploadDate to set
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
	 * @param photoID the photoID to set
	 */
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
}
