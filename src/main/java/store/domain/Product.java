package store.domain;

import store.domain.dto.ProductStockDto;
import store.domain.dto.PromotionDto;
import store.domain.dto.PurchaseCountDto;

import static camp.nextstep.edu.missionutils.DateTimes.now;
import static store.domain.Promotion.할인없음;

public abstract class Product {
    private final String name;
    private final Integer price;
    private final Integer stock;
    private final Promotion promotion;
    private final Integer promotionStock;

    public Product(String name, Integer price, Integer stock, Promotion promotion, Integer promotionStock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = promotion;
        this.promotionStock = promotionStock;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer verifyStock() {
        return stock + promotionStock;
    }

    public PurchaseProduct tryPurchasePromotion(Integer count) {
        PurchaseCountDto dto = verifyPromotion(count);
        return new PurchaseProduct(this, dto);
    }

    private PurchaseCountDto verifyPromotion(Integer count) {
        if(promotion == 할인없음) {
            return PurchaseCountDto.of(count);
        }
        return verifyPromotionDate(count);
    }

    private PurchaseCountDto verifyPromotionDate(Integer count) {
        if(promotion.isPromotionDate(now())) {
            PromotionDto dto = promotion.tryPurchaseProduct();
            return countPromotionProduct(dto, count);
        }
        return PurchaseCountDto.of(count);
    }

    private PurchaseCountDto countPromotionProduct(PromotionDto dto, Integer count) {
        ProductStockDto productStockDto = new ProductStockDto(stock, count, verifyStock(),
                dto.purchaseProduct(), dto.promotionProduct());
        Purchase purchase = new Purchase(productStockDto);
        return purchase.countPromotionProduct();
    }
}
