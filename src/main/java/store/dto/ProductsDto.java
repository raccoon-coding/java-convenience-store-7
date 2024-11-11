package store.dto;

import store.domain.Product;

import java.util.List;

public class ProductsDto {
    private static final String PREFIX = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n\n";
    private static final String SUFFIX = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    private final String products;

    public ProductsDto(List<Product> products) {
        this.products = construct(products);
    }

    public String getProducts() {
        return PREFIX + products + SUFFIX;
    }

    private String construct(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        products.forEach(product -> {
            transformPromotion(product, sb);
        });
        return sb.toString();
    }

    private void transformPromotion(Product product, StringBuilder sb) {
        sb.append(String.format("- %s %,d원 ", product.getName(), product.getPrice()));
        haveStock(product.getStock(), sb);
        havePromotion(product, sb);
        sb.append("\n");
    }

    private void havePromotion(Product product, StringBuilder sb) {
        if(product.isPromotionProduct()){
            sb.append(String.format("%s",  product.getPromotionName()));
        }
    }

    private void haveStock(Integer stock, StringBuilder sb) {
        if(stock > 0) {
            sb.append(String.format("%,d개 ", stock));
            return;
        }
        sb.append(String.format("%s", "재고 없음 "));
    }
}
