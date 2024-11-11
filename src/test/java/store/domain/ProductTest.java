package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.util.FileLeader;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    Promotions promotions;
    Products products;

    public ProductTest() {
        FileLeader leader = new FileLeader();
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("수량 확인")
    void 총_수량_확인() {
        Product product = products.findProductByNameNotPromotion("물");
        Integer expect = 10;
        Integer result = product.getStock();

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 수량과 일반 수량 확인")
    void 수량_변경_확인() {
        Product product = products.findProductByNameNotPromotion("사이다");
        Product promotion = products.findProductByNamePromotion("사이다");
        Integer expectStock = 7;
        Integer expectPromotionStock = 8;

        assertThat(product.getStock()).isEqualTo(expectStock);
        assertThat(promotion.getStock()).isEqualTo(expectPromotionStock);
    }

    @Test
    @DisplayName("구매 시도 확인")
    void 구매_물품_확인() {
        Product product = products.findProductByNameNotPromotion("사이다");
        Product promotion = products.findProductByNamePromotion("사이다");
        PurchaseProduct result = product.tryPurchasePromotion(10, promotion);

        assertThat(result.getPromotionCount()).isEqualTo(2);
    }
}
