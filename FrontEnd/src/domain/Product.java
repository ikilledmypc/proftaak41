package domain;

import java.util.logging.Logger;

public class Product {

    private int productID;
    private String name;
    private int amount;
    private Photo photo;
    private boolean sepia;
    private boolean blackWhite;
    private int cropX;
    private int cropY;
    private int cropWidth;
    private int cropHeight;

    public Product(int productID, String name, int amount, Photo photo, boolean sepia, boolean blackWhite, int cropX, int cropY, int cropWidth, int cropHeight, float materialPrice) {
        this.productID = productID;
        this.name = name;
        this.amount = amount;
        this.photo = photo;
        this.sepia = sepia;
        this.blackWhite = blackWhite;
        this.cropX = cropX;
        this.cropY = cropY;
        this.cropWidth = cropWidth;
        this.cropHeight = cropHeight;
        this.materialPrice = materialPrice;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *
     * @param productID
     * @param name
     * @param amount
     * @param materialPrice
     * @param photo
     */
    public Product(int productID, String name, int amount, float materialPrice, Photo photo) {
        this.productID = productID;
        this.name = name;
        this.amount = amount;
        this.materialPrice = materialPrice;
        this.photo = photo;
    }


    public Product(int productID, String name, float materialPrice) {
        super();
        this.productID = productID;
        this.name = name;
        this.materialPrice = materialPrice;
        this.amount = 1;
    }

    /**
     * Constructor
     *
     * @param name
     * @param materialPrice
     */
    public Product(String name, float materialPrice) {
        super();
        this.name = name;
        this.materialPrice = materialPrice;
        this.amount = 1;
    }


    public Product(int productID, String name, float materialPrice, Photo photo) {
        super();
        this.productID = productID;
        this.name = name;
        this.materialPrice = materialPrice;
        this.photo = photo;
        this.amount = 1;
    }

    public Product(String name, float materialPrice, Photo foto) {
        super();
        this.name = name;

        this.materialPrice = materialPrice;
        this.photo = foto;
        this.amount = 1;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    private float materialPrice;

    public Photo getPhoto() {
        return photo;
    }
    
        public boolean isSepia() {
        return sepia;
    }

    public void setSepia(boolean sepia) {
        this.sepia = sepia;
    }

    public boolean isBlackWhite() {
        return blackWhite;
    }

    public void setBlackWhite(boolean blackWhite) {
        this.blackWhite = blackWhite;
    }

    public int getCropX() {
        return cropX;
    }

    public void setCropX(int cropX) {
        this.cropX = cropX;
    }

    public int getCropY() {
        return cropY;
    }

    public void setCropY(int cropY) {
        this.cropY = cropY;
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidth = cropWidth;
    }

    public int getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.cropHeight = cropHeight;
    }
    

    /**
     * @return the materialPrice
     */
    public float getMaterialPrice() {
        return materialPrice;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * @param materialPrice the materialPrice to set
     */
    public void setMaterialPrice(float materialPrice) {
        this.materialPrice = materialPrice;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return this.name + " \u20ac " + this.materialPrice;
    }
    
    public String getIdentifier(){
        return ""+this.photo.getPhotoID()+","+this.productID+","+this.sepia+","+this.blackWhite+","+this.cropHeight+","+this.cropWidth+","+this.cropX+","+this.cropY;
    }

}
