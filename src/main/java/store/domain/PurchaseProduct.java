package store.domain;

import store.domain.dto.PurchaseCountDto;
import store.exception.DomainException;
import store.exception.OutOfStockException;

public class PurchaseProduct {
    private final Product product;
    private final Product promotionProduct;

    private int purchaseCount;
    private int purchasePromotionCount;
    private int promotionCount;
    private int addPromotionCount;

    public PurchaseProduct(Product product, Product promotionProduct, PurchaseCountDto dto) {
        this.product = product;
        this.promotionProduct = promotionProduct;

        this.purchaseCount = dto.purchaseCount();
        this.purchasePromotionCount = dto.purchasePromotionCount();
        this.promotionCount = dto.promotionCount();
        this.addPromotionCount = dto.addPromotionCount();
    }

    public String getProductName() {
        return product.getName();
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public int getPurchasePromotionCount() {
        return purchasePromotionCount;
    }

    public int getPromotionCount() {
        return promotionCount;
    }

    public int getAddPromotionCount() {
        return this.addPromotionCount;
    }

    public Integer getTotalPurchaseCount() {
        return this.purchaseCount + this.purchasePromotionCount + this.promotionCount;
    }

    public Integer getTotalPurchaseAmount() {
        return getTotalPurchaseCount() * this.product.getPrice();
    }

    public Integer getPromotionAmount() {
        return this.promotionCount * this.product.getPrice();
    }

    public Boolean isPromotionProduct() {
        return this.promotionCount >= 0;
    }

    public Boolean isNotPromotion() {
        return purchasePromotionCount > 0;
    }

    public Boolean isAddBuyProduct() {
        return addPromotionCount > 0;
    }

    public Integer getPromotionStock() {
        return promotionProduct.getStock();
    }

    public Integer getTotalStock() {
        return product.getStock() + promotionProduct.getStock();
    }

    public void addPromotionCount() {
        this.promotionCount += this.addPromotionCount;
        this.addPromotionCount = 0;
    }

    public void addPurchasePromotionCount() {
        this.purchaseCount += this.purchasePromotionCount;
        this.purchasePromotionCount = 0;
    }

    public void purchase() {
        isOverStock();
        Integer count = this.purchaseCount;
        if(this.promotionProduct == null) {
            product.purchase(count);
            return;
        }
        Integer promotion = this.promotionCount + this.purchasePromotionCount;
        verify(count, promotion);
    }

    private void isOverStock() {
        if(this.purchaseCount < 0) {
            throw new OutOfStockException(DomainException.재고_부족.getMessage());
        }
    }

    private void verify(Integer count, Integer promotion) {
        verifyPromotionStock(count, promotion);
        verifyPartPromotion(count, promotion);
        verifyStock(count);
    }

    private void verifyPromotionStock(Integer count, Integer promotionCount) {
        if(this.promotionProduct.getStock() >= count + promotionCount) {
            this.promotionProduct.purchase(count + promotionCount);
        }
    }

    private void verifyPartPromotion(Integer count, Integer promotionCount) {
        if(count + promotionCount >= this.promotionProduct.getStock() && this.promotionProduct.getStock() != 0) {
            this.product.purchase(count + promotionCount - this.promotionProduct.getStock());
            this.promotionProduct.purchase(this.promotionProduct.getStock());
        }
    }

    private void verifyStock(Integer count) {
        if(this.promotionProduct.getStock() == 0) {
            this.product.purchase(count);
        }
    }
}
