package store.dto;

import store.domain.PurchaseProduct;

public class NotPromotionDto {
    private final String productName;
    private final Integer productCount;

    public NotPromotionDto(PurchaseProduct product) {
        this.productName = product.getProductName();
        this.productCount = product.getPurchasePromotionCount();
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCount() {
        return productCount;
    }
}
