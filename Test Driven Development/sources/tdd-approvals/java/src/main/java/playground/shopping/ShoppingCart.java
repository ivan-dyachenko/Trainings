package playground.shopping;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivan Dyachenko http://atgrand.com
 * Date: 12/16/12
 * Time: 6:28 PM
 */
public class ShoppingCart {

    private List<Product> products = new ArrayList<Product>();

    private Integer total;

    public void add(Product product) {
        products.add(product);
    }


    public void confirm() {
        total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                ", total=" + total +
                '}';
    }
}
