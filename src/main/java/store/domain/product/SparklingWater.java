package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

public class SparklingWater extends Product {
    public SparklingWater(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        super(name, price, stock, promotion, promotionStock);
    }

}
