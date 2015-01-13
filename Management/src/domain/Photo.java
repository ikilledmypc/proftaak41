package domain;

import java.util.Date;

/**
 *
 * @author mikerooijackers
 */
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

    /**
     *
     * @param photoID
     * @param uploadDate
     * @param price
     */
    public Photo(int photoID, Date uploadDate, float price) {
        this.photoID = photoID;
        this.uploadDate = uploadDate;
        this.price = price;
    }

    /**
     *
     * @param string
     * @param photo1jpg
     * @param mb
     */
    public Photo(String string, String photo1jpg, String mb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
