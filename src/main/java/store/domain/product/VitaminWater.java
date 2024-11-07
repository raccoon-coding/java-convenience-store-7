package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class VitaminWater extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.할인없음, 6
    );

    public VitaminWater(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}