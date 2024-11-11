package store.domain.dto;

import store.domain.Product;

public record ProductStockDto(Integer purchaseCount, Product defaultProduct, Product promotionProduct, Integer buyCount, Integer getCount) {

    public static ProductStockDto of(Integer purchaseCount, Product defaultProduct, PromotionDto dto) {
        return new ProductStockDto(purchaseCount, defaultProduct, null, dto.buyCount(), dto.getCount());
    }
}
