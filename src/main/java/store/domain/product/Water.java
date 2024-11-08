package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

public class Water extends Product {
    public Water(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        super(name, price, stock, promotion, promotionStock);
    }

}
