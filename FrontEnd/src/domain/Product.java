package domain;

/**
 *
 * @author mikerooijackers
 */
public class Product {

    /**
     *
     * @param amount
     */
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

    /**
     *
     * @param productID
     * @param name
     * @param materialPrice
     */
    public Product(int productID, String name, float materialPrice) {
		super();
		this.productID = productID;
		this.name = name;
		this.materialPrice = materialPrice;
                this.amount =1;
	}

	private int productID;
	private String name;
        private int amount;

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

    /**
     *
     * @return
     */
    public Photo getPhoto() {
		return photo;
	}

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
                 this.amount =1;
	}

    /**
     *
     * @param productID
     * @param name
     * @param materialPrice
     * @param photo
     */
    public Product(int productID, String name, float materialPrice,Photo photo) {
		super();
		this.productID = productID;                
		this.name = name;
		this.materialPrice = materialPrice;
		this.photo = photo;
                 this.amount =1;
	}

    /**
     *
     * @param name
     * @param materialPrice
     * @param foto
     */
    public Product(String name, float materialPrice, Photo foto) {
		super();
		this.name = name;
                
		this.materialPrice = materialPrice;
		this.photo = foto;
                 this.amount =1;
	}
	/**
	 * @return the materialPrice
	 */
	public float getMaterialPrice() {
		return materialPrice;
	}
	
    /**
     *
     * @param photo
     */
    public void setPhoto(Photo photo) {
		this.photo = photo;
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
        
        @Override
        public String toString(){
            return this.name + " \u20ac "+this.materialPrice;
        }
}
