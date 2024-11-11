package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.dto.ProductStockDto;
import store.domain.dto.PurchaseCountDto;
import store.util.FileLeader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    Promotions promotions;
    Products products;

    public PurchaseTest() {
        FileLeader leader = new FileLeader();
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("프로모션 재고가 없고, 전체 재고는 존재하여 일반 구매로 진행")
    void 프로모션_재고_없음_확인() {
        Integer buy = 7;
        Integer purchaseProduct = 1;
        Integer promotionProduct = 0;
        String name = "물";
        Product product = products.findProductByNameNotPromotion(name);
        Product promotion = products.findProductByNamePromotion(name);

        ProductStockDto dto = new ProductStockDto(buy, product, promotion, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        Integer expect = 0;
        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 부분 존재하고, 전체 재고는 존재하여 일반 + 프로모션 구매로 진행")
    void 프로모션_재고_부분_있음_확인() {
        Integer buy = 7;
        Integer purchaseProduct = 1;
        Integer promotionProduct = 1;
        String name = "감자칩";
        Product product = products.findProductByNameNotPromotion(name);
        Product promotion = products.findProductByNamePromotion(name);

        ProductStockDto dto = new ProductStockDto(buy, product, promotion, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        Integer expect = 1;
        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 존재하고, 전체 재고는 존재하여 프로모션 구매로 진행")
    void 프로모션_재고_있음_확인() {
        Integer buy = 7;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        String name = "콜라";
        Product product = products.findProductByNameNotPromotion(name);
        Product promotion = products.findProductByNamePromotion(name);

        ProductStockDto dto = new ProductStockDto(buy, product, promotion, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        Integer expect = 2;
        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.promotionCount()).isEqualTo(expect);
    }

    @Test
    @DisplayName("프로모션 재고가 존재하고, 프로모션을 완성하기 위해 추가 요청 진행")
    void 프로모션_재고_있음_추가_구매_요청_확인() {
        Integer buy = 5;
        Integer purchaseProduct = 2;
        Integer promotionProduct = 1;
        String name = "사이다";
        Product product = products.findProductByNameNotPromotion(name);
        Product promotion = products.findProductByNamePromotion(name);

        ProductStockDto dto = new ProductStockDto(buy, product, promotion, purchaseProduct, promotionProduct);
        Purchase purchase = new Purchase(dto);

        Integer expect = 1;
        PurchaseCountDto result = purchase.countPromotionProduct();
        assertThat(result.addPromotionCount()).isEqualTo(expect);
    }
}
