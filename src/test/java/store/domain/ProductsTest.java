package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductsTest {
    @Test
    @DisplayName("물품이 제대로 나오는지 확인")
    void 물품_검색_확인() {
        Product result = Products.findProductByName("콜라");
        Product expect = Products.콜라.getProduct();
        assertThat(result.getName()).isEqualTo(expect.getName());
    }
}