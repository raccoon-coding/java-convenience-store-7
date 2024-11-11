package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.util.FileLeader;

import static org.assertj.core.api.Assertions.assertThat;

class ProductsTest {
    Promotions promotions;
    Products products;

    public ProductsTest() {
        FileLeader leader = new FileLeader();
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("src/main/resources/products.md", promotions));
    }

    @Test
    @DisplayName("물품이 제대로 나오는지 확인")
    void 물품_검색_확인() {
        Product result = products.findProductByNameNotPromotion("물");
        assertThat(result.getName()).isEqualTo("물");
    }
}
