package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class LunchBox extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.할인없음, 8
    );

    public LunchBox(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
