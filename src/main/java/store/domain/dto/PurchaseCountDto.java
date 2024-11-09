package store.domain.dto;

public record PurchaseCountDto(Integer purchaseStock, Integer purchasePromotionStock, Integer promotionCount, Integer addPurchaseCount, Integer addPromotionCount) {
    public static PurchaseCountDto of(Integer count) {
        return new PurchaseCountDto(count, 0, 0, 0, 0);
    }
}
