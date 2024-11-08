package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class CupRamen extends Product {
    public CupRamen(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        super(name, price, stock, promotion, promotionStock);
    }

}
