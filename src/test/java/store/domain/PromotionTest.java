package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.dto.PromotionDto;
import store.util.FileLeader;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionTest {
    Promotions promotions;
    Products products;

    public PromotionTest() {
        FileLeader leader = new FileLeader();
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("탄산 할인은 2024년 12월 31일까지 입니다.")
    void 탄산_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2025, 1,1,0,0,0);
        Promotion promotion = promotions.findByName("탄산할인");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("탄산 할인은 2024년 12월 31일까지 입니다.")
    void 탄산_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 5,1,0,0,0);
        Promotion promotion = promotions.findByName("탄산할인");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }

    @Test
    @DisplayName("MD추천상품 할인은 2024년 12월 31일까지 입니다.")
    void MD추천_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2025, 1,1,0,0,0);
        Promotion promotion = promotions.findByName("MD추천상품");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("MD추천상품 할인은 2024년 12월 31일까지 입니다.")
    void MD추천_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 11,1,0,0,0);
        Promotion promotion = promotions.findByName("MD추천상품");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }

    @Test
    @DisplayName("반짝할인 할인은 2024년 11월 30일까지 입니다.")
    void 반짝_할인_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 12,1,0,0,0);
        Promotion promotion = promotions.findByName("반짝할인");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(false);
    }

    @Test
    @DisplayName("반짝할인 할인은 2024년 11월 30일까지 입니다.")
    void 반짝_할인_통과_확인() {
        LocalDateTime now = LocalDateTime.of(2024, 11,1,0,0,0);
        Promotion promotion = promotions.findByName("반짝할인");
        assertThat(promotion.isPromotionDate(now)).isEqualTo(true);
    }

    @Test
    @DisplayName("탄산 할인은 2+1 입니다.")
    void 탄산_할인_프로모션_개수는_1_입니다() {
        Promotion promotion = promotions.findByName("탄산할인");
        Integer purchaseExpect = 2;
        Integer promotionExpect = 1;
        PromotionDto result = promotion.tryPurchaseProduct();
        assertThat(result.buyCount()).isEqualTo(purchaseExpect);
        assertThat(result.getCount()).isEqualTo(promotionExpect);
    }

    @Test
    @DisplayName("MD추천할인은 1+1 입니다.")
    void MD추천_할인_프로모션_개수는_1_입니다() {
        Promotion promotion = promotions.findByName("MD추천할인");
        Integer purchaseExpect = 1;
        Integer promotionExpect = 1;
        PromotionDto result = promotion.tryPurchaseProduct();
        assertThat(result.buyCount()).isEqualTo(purchaseExpect);
        assertThat(result.getCount()).isEqualTo(promotionExpect);
    }

    @Test
    @DisplayName("반짝 할인은 1+1 입니다.")
    void 반짝_할인_프로모션_개수는_1_입니다() {
        Promotion promotion = promotions.findByName("반짝할인");
        Integer purchaseExpect = 1;
        Integer promotionExpect = 1;
        PromotionDto result = promotion.tryPurchaseProduct();
        assertThat(result.buyCount()).isEqualTo(purchaseExpect);
        assertThat(result.getCount()).isEqualTo(promotionExpect);
    }
}
