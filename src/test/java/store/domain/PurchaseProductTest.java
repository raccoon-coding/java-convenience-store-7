package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.dto.PurchaseCountDto;
import store.util.FileLeader;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseProductTest {
    Promotions promotions;
    Products products;

    public PurchaseProductTest() {
        FileLeader leader = new FileLeader();
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("추가 구매하여 구매량 변경 확인")
    void 추가_구매_확인() {
        Integer purchaseCount = 4;
        Integer purchasePromotionCount = 0;
        Integer promotionCount = 2;
        Integer addPromotionCount = 0;

        Product product = products.findProductByNameNotPromotion("사이다");
        Product promotion = products.findProductByNamePromotion("사이다");
        PurchaseCountDto dto = new PurchaseCountDto(purchaseCount, purchasePromotionCount, promotionCount, addPromotionCount);
        PurchaseProduct result = new PurchaseProduct(product, promotion, dto);
        result.addPromotionCount();

        String expectName = "사이다";
        Integer expectStock = 0;
        Integer expectPurchaseCount = 6;
        Integer expectPromotionCount = 3;

        assertThat(result.getProductName()).isEqualTo(expectName);
        assertThat(result.getPurchaseCount()).isEqualTo(expectStock);
        assertThat(result.getPurchasePromotionCount()).isEqualTo(expectPurchaseCount);
        assertThat(result.getPromotionCount()).isEqualTo(expectPromotionCount);
    }

    @Test
    @DisplayName("감자칩 9개 구입, 프로모션 2개 증정")
    void 구매_프로모션_초과_확인() {
        String expectName = "감자칩";
        Integer purchaseCount = 9;
        Product product = products.findProductByNameNotPromotion(expectName);
        Product promotion = products.findProductByNamePromotion(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount, promotion);
        purchaseProduct.purchase();

        Integer expectStock = 1;
        Integer expectPromotionStock = 0;
        Integer expectPromotionCount = 2;
        Product result = products.findProductByNameNotPromotion(expectName);
        Product resultPromotion = products.findProductByNamePromotion(expectName);
        assertThat(result.getStock()).isEqualTo(expectStock);
        assertThat(resultPromotion.getStock()).isEqualTo(expectPromotionStock);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotionCount);
    }
}
