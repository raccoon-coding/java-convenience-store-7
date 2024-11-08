package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.dto.ProductStockDto;
import store.domain.dto.PurchaseCountDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    @Test
    @DisplayName("프로모션 재고가 없고, 전체 재고는 존재하여 일반 구매로 진행")
    void 프로모션_재고_없음_확인() {
        Integer stock = 2;
        Integer purchaseCount = 10;
        Integer totalStock = 10;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        Integer expect = 0;

        ProductStockDto dto = new ProductStockDto(stock, purchaseCount, totalStock, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 부분 존재하고, 전체 재고는 존재하여 일반 + 프로모션 구매로 진행")
    void 프로모션_재고_부분_있음_확인() {
        Integer stock = 3;
        Integer purchaseCount = 10;
        Integer totalStock = 10;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;

        ProductStockDto dto = new ProductStockDto(stock, purchaseCount, totalStock, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        Integer expect = 1;
        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 존재하고, 전체 재고는 존재하여 프로모션 구매로 진행")
    void 프로모션_재고_있음_확인() {
        Integer stock = 9;
        Integer purchaseCount = 9;
        Integer totalStock = 19;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        Integer expect = 3;

        ProductStockDto dto = new ProductStockDto(stock, purchaseCount, totalStock, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 존재하고, 프로모션을 완성하기 위해 추가 요청 진행")
    void 프로모션_재고_있음_추가_구매_요청_확인() {
        Integer stock = 9;
        Integer purchaseCount = 8;
        Integer totalStock = 19;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        Integer expect = 1;

        ProductStockDto dto = new ProductStockDto(stock, purchaseCount, totalStock, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.addPurchaseCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 존재하고, 프로모션을 완성하기 위해 추가 요청 2개 진행")
    void 프로모션_재고_있음_추가_구매_요청_2개_확인() {
        Integer stock = 9;
        Integer purchaseCount = 7;
        Integer totalStock = 19;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        Integer expect = 2;

        ProductStockDto dto = new ProductStockDto(stock, purchaseCount, totalStock, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.addPurchaseCount()).isEqualTo(expect);
    }
}