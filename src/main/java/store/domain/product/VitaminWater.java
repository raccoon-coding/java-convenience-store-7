package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

public class VitaminWater extends Product {
    public VitaminWater(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        super(name, price, stock, promotion, promotionStock);
    }

}
