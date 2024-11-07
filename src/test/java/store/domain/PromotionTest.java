package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {
    @Test
    @DisplayName("탄산 할인은 2024년 12월 31일까지 입니다.")
    void 탄산_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2025, 1,1,0,0,0);
        Promotion promotion = Promotion.탄산할인;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("탄산 할인은 2024년 12월 31일까지 입니다.")
    void 탄산_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 5,1,0,0,0);
        Promotion promotion = Promotion.탄산할인;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }

    @Test
    @DisplayName("MD추천상품 할인은 2024년 12월 31일까지 입니다.")
    void MD추천_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2025, 1,1,0,0,0);
        Promotion promotion = Promotion.MD추천상품;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("MD추천상품 할인은 2024년 12월 31일까지 입니다.")
    void MD추천_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 11,1,0,0,0);
        Promotion promotion = Promotion.MD추천상품;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }

    @Test
    @DisplayName("반짝할인 할인은 2024년 11월 30일까지 입니다.")
    void 반짝_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 12,1,0,0,0);
        Promotion promotion = Promotion.반짝할인;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("반짝할인 할인은 2024년 11월 30일까지 입니다.")
    void 반짝_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 11,1,0,0,0);
        Promotion promotion = Promotion.반짝할인;
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }
}