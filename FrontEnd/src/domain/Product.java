package domain;

public class Product {


	public Product(int productID, String name, float materialPrice) {
		super();
		this.productID = productID;
		this.name = name;
		this.materialPrice = materialPrice;
	}

	private int productID;
	private String name;
	private float materialPrice;
	private Photo photo;

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
	}
	public Product(int productID, String name, float materialPrice,Photo photo) {
		super();
		this.productID = productID;
		this.name = name;
		this.materialPrice = materialPrice;
		this.photo = photo;
	}


	public Product(String name, float materialPrice, Photo foto) {
		super();
		this.name = name;
		this.materialPrice = materialPrice;
		this.photo = foto;
	}
        
        @Override
        public String toString(){
            return this.name + " | \u20ac"+this.materialPrice;
        }
        
	/**
	 * @return the materialPrice
	 */
	public float getMaterialPrice() {
		return materialPrice;
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
