package store.domain.dto;

public record PurchaseCountDto(Integer purchaseCount, Integer promotionCount, Integer addPurchaseCount) {
    public static PurchaseCountDto of(Integer count) {
        return new PurchaseCountDto(count, 0, 0);
    }
}
