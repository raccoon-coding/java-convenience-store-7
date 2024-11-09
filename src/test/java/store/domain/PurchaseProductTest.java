package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.dto.PurchaseCountDto;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseProductTest {

    @Test
    @DisplayName("추가 구매하여 구매량 변경 확인")
    void 추가_구매_확인() {
        Integer purchaseStock = 0;
        Integer promotionStock = 5;
        Integer promotionCount = 2;
        Integer addPurchaseCount = 1;
        Integer addPromotionCount = 1;

        Product product = Products.사이다.getProduct();
        PurchaseCountDto dto = new PurchaseCountDto(purchaseStock, promotionStock, promotionCount, addPurchaseCount, addPromotionCount);
        PurchaseProduct result = new PurchaseProduct(product, dto);
        result.addPurchaseCount();

        String expectName = "사이다";
        Integer expectStock = 0;
        Integer expectPurchaseCount = 6;
        Integer expectPromotionCount = 3;

        assertThat(result.getProduct().getName()).isEqualTo(expectName);
        assertThat(result.getPurchaseStock()).isEqualTo(expectStock);
        assertThat(result.getPurchasePromotionStock()).isEqualTo(expectPurchaseCount);
        assertThat(result.getPromotionCount()).isEqualTo(expectPromotionCount);
    }

    @Test
    @DisplayName("콜라 9개 구입시도")
    void 구매_시도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 6;
        Integer expectPromotion = 3;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(9);

        assertThat(purchaseProduct.getProduct()).isEqualTo(product);
        assertThat(purchaseProduct.getPurchasePromotionStock()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
    }

    @Test
    @DisplayName("콜라 8개 구입시도")
    void 구매_시도_추가_구입_유도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 6;
        Integer expectAddPromotion = 1;
        Integer expectPromotion = 2;
        Integer purchaseCount = 8;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount);

        assertThat(purchaseProduct.getProduct()).isEqualTo(product);
        assertThat(purchaseProduct.getPurchasePromotionStock()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
        assertThat(purchaseProduct.getAddPromotionCount()).isEqualTo(expectAddPromotion);
    }

    @Test
    @DisplayName("콜라 7개 구입시도")
    void 구매_시도_추가_구입_2개_유도_확인() {
        String expectName = "콜라";
        Integer expectPurchase = 5;
        Integer expectAddPurchase = 1;
        Integer expectAddPromotion = 1;
        Integer expectPromotion = 2;
        Integer purchaseCount = 7;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount);

        assertThat(purchaseProduct.getProduct()).isEqualTo(product);
        assertThat(purchaseProduct.getPurchasePromotionStock()).isEqualTo(expectPurchase);
        assertThat(purchaseProduct.getPromotionCount()).isEqualTo(expectPromotion);
        assertThat(purchaseProduct.getAddPurchaseCount()).isEqualTo(expectAddPurchase);
        assertThat(purchaseProduct.getAddPromotionCount()).isEqualTo(expectAddPromotion);
    }

    @Test
    @DisplayName("콜라 9개 구입")
    void 구매_확인() {
        String expectName = "콜라";
        Integer purchaseCount = 9;
        Product product = Products.findProductByName(expectName);
        PurchaseProduct purchaseProduct = product.tryPurchasePromotion(purchaseCount);
        purchaseProduct.purchase();

        Integer expectStock = 10;
        Integer expectPromotionStock = 1;
        Product result = Products.findProductByName(expectName);
        assertThat(result.getStock()).isEqualTo(expectStock);
        assertThat(result.getPromotionStock()).isEqualTo(expectPromotionStock);
    }
}
