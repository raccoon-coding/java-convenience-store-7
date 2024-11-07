package store.domain.product;

import store.domain.Product;
import store.domain.Promotion;

import java.util.Map;

public class OrangeJuice extends Product {
    private static final Map<Promotion, Integer> DEFAULT_PROMOTIONS = Map.of(
            Promotion.MD추천상품, 9
    );

    public OrangeJuice(String name, Integer price) {
        super(name, price, DEFAULT_PROMOTIONS);
    }

}
