package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class EnergyBar extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.할인없음, 5
    );

    public EnergyBar(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
