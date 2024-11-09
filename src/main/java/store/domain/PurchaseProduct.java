package store.domain;

import store.domain.dto.PurchaseCountDto;

public class PurchaseProduct {
    private final Product product;
    private int purchaseStock;
    private int purchasePromotionStock;
    private int promotionCount;
    private int addPurchaseCount;
    private int addPromotionCount;


    public PurchaseProduct(Product product, PurchaseCountDto dto) {
        this.product = product;
        this.purchaseStock = dto.purchaseStock();
        this.purchasePromotionStock = dto.purchasePromotionStock();
        this.promotionCount = dto.promotionCount();
        this.addPurchaseCount = dto.addPurchaseCount();
        this.addPromotionCount = dto.addPromotionCount();
    }

    public Product getProduct() {
        return product;
    }

    public int getPurchaseStock() {
        return purchaseStock;
    }

    public int getPurchasePromotionStock() {
        return purchasePromotionStock;
    }

    public int getPromotionCount() {
        return promotionCount;
    }

    public int getAddPurchaseCount() {
        return addPurchaseCount;
    }

    public int getAddPromotionCount() {
        return addPromotionCount;
    }

    public void addPurchaseCount() {
        purchasePromotionStock += addPurchaseCount;
        promotionCount += addPromotionCount;
        addPurchaseCount = 0;
        addPromotionCount = 0;
    }

    public void purchase() {
        product.purchase(purchaseStock, purchasePromotionStock + promotionCount);
    }
}
