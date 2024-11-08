package store.domain.dto;

public record PromotionDto(Integer purchaseProduct, Integer promotionProduct) {

    public static PromotionDto of() {
        return new PromotionDto(1, 0);
    }
}
