package store.domain.dto;

public record PromotionDto(Integer buyCount, Integer getCount) {

    public static PromotionDto of() {
        return new PromotionDto(1, 0);
    }
}
