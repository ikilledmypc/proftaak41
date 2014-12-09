package domain;

import controllers.DatabaseController;

public class Product {

	private int productID;
	private String name;
	private int amount;
	private float materialPrice;
	private Photo photo;
	

	public Product(int productID, String name, int amount, float materialPrice,
			Photo photo) {
		super();
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

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addToDB(){
		DatabaseController db = DatabaseController.getInstance();
		db.insert("insert into product (name,materialprice) values('"+this.name+"',"+this.materialPrice+")");
		db.closeConnection();
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

	public Photo getPhoto() {
		return photo;
	}

	public int getAmount() {
		return amount;
	}

	public void addAmount(int amount) {
		this.amount += amount;
	}

	/**
	 * @param materialPrice
	 *            the materialPrice to set
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
	 * @param name
	 *            the name to set
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
	 * @param productID
	 *            the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
}
