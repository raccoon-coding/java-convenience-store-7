package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class Sprite extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.탄산할인, 8,
            Promotion.할인없음, 8
    );

    public Sprite(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
