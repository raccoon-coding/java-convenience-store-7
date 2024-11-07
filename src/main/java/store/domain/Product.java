package store.domain;

import java.util.EnumMap;
import java.util.Map;

public abstract class Product {
    private final String name;
    private final Integer price;
    private final Map<Promotion, Integer> productCounts;

    public Product(String name, Integer price, Map<Promotion, Integer> productCounts) {
        this.name = name;
        this.price = price;
        this.productCounts = new EnumMap<>(productCounts);
    }

    public String getName() {
        return name;
    }

    private void purchase(Integer count) {

    }
}
