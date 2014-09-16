package domain;

public class Product {
	private int productID;
	private String name;
	private float materialPrice;
	/**
	 * @return the materialPrice
	 */
	public float getMaterialPrice() {
		return materialPrice;
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
}
