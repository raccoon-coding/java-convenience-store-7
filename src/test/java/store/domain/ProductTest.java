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
        Integer result = product.getTotalStock();

        assertThat(result).isEqualTo(expect);
    }


}
