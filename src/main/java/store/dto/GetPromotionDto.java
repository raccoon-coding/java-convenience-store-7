package store.dto;

import store.domain.PurchaseProduct;

public class GetPromotionDto {
    private final String productName;
    private final Integer promotionCount;

    public GetPromotionDto(PurchaseProduct product) {
        this.productName = product.getProductName();
        this.promotionCount = product.getAddPromotionCount();
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPromotionCount() {
        return promotionCount;
    }
}
