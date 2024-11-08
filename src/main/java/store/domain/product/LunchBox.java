package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

public class LunchBox extends Product {
    public LunchBox(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        super(name, price, stock, promotion, promotionStock);
    }
}
