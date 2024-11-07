package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class Water extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.할인없음, 10
    );

    public Water(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
