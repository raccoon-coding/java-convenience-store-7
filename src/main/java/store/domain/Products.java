package store.domain;

import store.exception.NotContainProduct;

import java.util.List;
import java.util.Optional;

import static store.exception.DomainException.존재하지_않는_상품;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Product findProductByNameNotPromotion(String name) {
        Optional<Product> optional = products.stream()
                .filter(product -> product.getName().equals(name) && !product.isPromotionProduct())
                .findFirst();
        return verifyProduct(optional);
    }

    public List<Product> getProducts() {
        return products.stream().toList();
    }

    public Product findProductByNamePromotion(String name) {
        Optional<Product> optional = products.stream()
                .filter(product -> product.getName().equals(name) && product.isPromotionProduct())
                .findFirst();
        return verifyPromotion(optional);
    }

    private Product verifyProduct(Optional<Product> optional) {
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new NotContainProduct(존재하지_않는_상품.getMessage());
    }

    private Product verifyPromotion(Optional<Product> optional) {
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
