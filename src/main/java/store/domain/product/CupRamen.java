package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class CupRamen extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.MD추천상품, 1,
            Promotion.할인없음, 10
    );

    public CupRamen(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }
}
