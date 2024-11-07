package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class Cola extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.탄산할인, 10,
            Promotion.할인없음, 10
    );

    public Cola(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}