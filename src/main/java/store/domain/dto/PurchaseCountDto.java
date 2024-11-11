package store.domain.dto;

public record PurchaseCountDto(Integer purchaseCount, Integer purchasePromotionCount, Integer promotionCount, Integer addPromotionCount) {
    public static PurchaseCountDto of(Integer count) {
        return new PurchaseCountDto(count, 0, 0, 0);
    }
}
