package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("총 수량 확인")
    void 총_수량_확인() {
        Product product = Products.findProductByName("콜라");
        Integer expect = 20;
        Integer result = product.verifyStock();

        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("콜라 9개 구입시도")
    void 구매_시도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 6;
        Integer expectPromotion = 3;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(9);

        assertThat(purchaseProduct.getName()).isEqualTo(expectName);
        assertThat(purchaseProduct.getPurchaseCount()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
    }

    @Test
    @DisplayName("콜라 8개 구입시도")
    void 구매_시도_추가_구입_유도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 6;
        Integer expectAddPurchase = 1;
        Integer expectPromotion = 2;
        Integer purchaseCount = 8;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount);

        assertThat(purchaseProduct.getName()).isEqualTo(expectName);
        assertThat(purchaseProduct.getPurchaseCount()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
        assertThat(purchaseProduct.getAddPurchaseCount()).isEqualTo(expectAddPurchase);
    }

    @Test
    @DisplayName("콜라 7개 구입시도")
    void 구매_시도_추가_구입_2개_유도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 5;
        Integer expectAddPurchase = 2;
        Integer expectPromotion = 2;
        Integer purchaseCount = 7;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount);

        assertThat(purchaseProduct.getName()).isEqualTo(expectName);
        assertThat(purchaseProduct.getPurchaseCount()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
        assertThat(purchaseProduct.getAddPurchaseCount()).isEqualTo(expectAddPurchase);
    }
}