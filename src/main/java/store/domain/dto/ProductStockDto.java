package store.domain.dto;

public record ProductStockDto(Integer stock, Integer purchaseCount, Integer totalStock, Integer purchaseProduct, Integer promotionProduct) {

    public static ProductStockDto of(Integer promotionStock, Integer purchaseCount, Integer totalStock, PromotionDto dto) {
        return new ProductStockDto(promotionStock, purchaseCount, totalStock, dto.purchaseProduct(), dto.promotionProduct());
    }
}
