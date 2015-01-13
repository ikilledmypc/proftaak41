package domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mikerooijackers
 */
public class ShoppingCart {

    /*
     key = productid +photoid
     */
    HashMap<String, Product> products = new HashMap<>();

    /**
     *
     * @param p
     */
    public void putProduct(Product p) {
        String key = p.getIdentifier();
        Product productex = products.get(key);
        if (p != null) {
            productex.addAmount(1);
        }
        products.put(key, p);
    }

    /**
     *
     * @param key
     */
    public void removeProduct(String key) {
        Product p = products.get(key);
        p.setAmount(p.getAmount() - 1);
        if (p.getAmount() == 0) {
            this.products.remove(key);
        }
    }

    /**
     *
     * @return
     */
    public int getItemCount() {
        int amount = 0;
        for (Product p : products.values()) {
            amount += p.getAmount();
        }
        return amount;
    }

    /**
     *
     * @return
     */
    public ArrayList<Product> GetProducts() {
        ArrayList<Product> arrproducts = new ArrayList<>();
        for (Product op : products.values()) {
            arrproducts.add(op);
        }
        return arrproducts;
    }

}
