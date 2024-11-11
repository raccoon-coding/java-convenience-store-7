package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.util.FileLeader;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptTest {
    Promotions promotions;
    Products products;

    public ReceiptTest() {
        FileLeader leader = new FileLeader();
        this.promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        this.products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("영수증에 모든 상품 주문 내역이 다 들어가 있어야 한다.")
    void 레시피_내역_확인() {
        Map<String, Integer> input = Map.of("초코바", 5,
                "에너지바", 5);

        Receipt receipt = new Receipt(input, products);
        PurchaseProduct result = receipt.findProductName("초코바");
        Product expect = products.findProductByNamePromotion("초코바");
        Integer expectPromotionCount = 2;
        Integer expectBuyPromotionCount = 3;

        assertThat(result.getProductName()).isEqualTo(expect.getPromotionName());
        assertThat(result.getPromotionCount()).isEqualTo(expectPromotionCount);
        assertThat(result.getPurchaseCount()).isEqualTo(expectBuyPromotionCount);
    }
}
