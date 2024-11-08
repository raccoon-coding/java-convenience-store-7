package store.domain;

import store.domain.dto.PurchaseCountDto;

public class PurchaseProduct {
    private final String name;
    private final Integer price;
    private int purchaseCount;
    private int addPurchaseCount;
    private int promotionCount;

    public PurchaseProduct(Product product, PurchaseCountDto dto) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.purchaseCount = dto.purchaseCount();
        this.addPurchaseCount = dto.addPurchaseCount();
        this.promotionCount = dto.promotionCount();
    }

    public String getName() {
        return name;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public int getPromotionCount() {
        return promotionCount;
    }

    public int getAddPurchaseCount() {
        return addPurchaseCount;
    }
}
