package store.handler.message.dto;

import java.util.Map;

public class RequestPurchaseProducts {
    private final Map<String, Integer> products;

    public RequestPurchaseProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }
}
