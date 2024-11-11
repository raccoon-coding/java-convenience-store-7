package store.domain;

import store.domain.dto.ProductStockDto;
import store.domain.dto.PromotionDto;
import store.domain.dto.PurchaseCountDto;

import static camp.nextstep.edu.missionutils.DateTimes.now;

public class Product {
    private final String name;
    private final Integer price;
    private Integer stock;
    private final Promotion promotion;

    public Product(String name, Integer price, Integer stock, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void purchase(Integer count) {
        this.stock -= count;
    }

    public Boolean isPromotionProduct() {
        return this.promotion != null;
    }

    public Integer getStock() {
        return stock;
    }

    public String getPromotionName() {
        return promotion.getName();
    }

    public PurchaseProduct tryPurchasePromotion(Integer count, Product promotion) {
        PurchaseCountDto dto = verifyPromotion(count, promotion);
        return new PurchaseProduct(this, promotion, dto);
    }

    private PurchaseCountDto verifyPromotion(Integer count, Product promotion) {
        if(promotion == null) {
            ProductStockDto productStockDto = new ProductStockDto(count, this, null,
                    1, 0);
            Purchase purchase = new Purchase(productStockDto);
            return purchase.countPromotionProduct();
        }
        return verifyPromotionDate(count, promotion);
    }

    private PurchaseCountDto verifyPromotionDate(Integer count, Product product) {
        if(product.promotion.isPromotionDate(now())) {
            PromotionDto dto = product.promotion.tryPurchaseProduct();
            return countPromotionProduct(dto, product, count);
        }
        ProductStockDto productStockDto = new ProductStockDto(count, this, product,
                1, 0);
        Purchase purchase = new Purchase(productStockDto);
        return purchase.countPromotionProduct();
    }

    private PurchaseCountDto countPromotionProduct(PromotionDto dto, Product product, Integer count) {
        ProductStockDto productStockDto = new ProductStockDto(count, this, product,
                dto.buyCount(), dto.getCount());
        Purchase purchase = new Purchase(productStockDto);
        return purchase.countPromotionProduct();
    }
}
