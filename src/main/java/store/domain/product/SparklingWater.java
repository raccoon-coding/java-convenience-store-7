package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.EnumMap;
import java.util.Map;

public class SparklingWater extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.탄산할인, 5
    );

    public SparklingWater(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
