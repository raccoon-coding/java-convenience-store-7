package store.domain;

import store.domain.dto.ProductStockDto;
import store.domain.dto.PurchaseCountDto;
import store.exception.OutOfStockException;

import static store.exception.DomainException.재고_부족;

public class Purchase {
    private final Integer promotionStock;
    private final Integer buyCount;
    private final Integer totalStock;
    private final Integer purchaseProduct;
    private final Integer promotionProduct;

    private Integer tryPurchaseStockCount;
    private Integer tryPurchasePromotionStockCount;
    private Integer tryPromotionCount;
    private Integer tryAddPurchaseCount;
    private Integer tryAddPromotionCount;

    public Purchase(ProductStockDto dto) {
        this.promotionStock = dto.stock();
        this.buyCount = dto.purchaseCount();
        this.totalStock = dto.totalStock();
        this.purchaseProduct = dto.purchaseProduct();
        this.promotionProduct = dto.promotionProduct();
    }

    public PurchaseCountDto countPromotionProduct() {
        buyPromotion();
        buyPortionPromotion();
        buyNoPromotion();
        outOfStock();
        return new PurchaseCountDto(tryPurchaseStockCount, tryPurchasePromotionStockCount,
                tryPromotionCount, tryAddPurchaseCount, tryAddPromotionCount);
    }

    private void buyPromotion() {
        if(promotionStock >= purchaseProduct + promotionProduct && promotionStock >= buyCount) {
            tryPurchaseStockCount = 0;
            tryPromotionCount = buyCount / (purchaseProduct + promotionProduct);
            tryPurchasePromotionStockCount = buyCount - tryPromotionCount;
            tryAddPurchaseCount = purchaseProduct + promotionProduct - buyCount % (purchaseProduct + promotionProduct) - 1;
            tryAddPromotionCount = 1;
        }
    }

    private void buyPortionPromotion() {
        if(promotionStock >= purchaseProduct + promotionProduct && promotionStock < buyCount) {
            tryPromotionCount = promotionStock / (purchaseProduct + promotionProduct);
            tryPurchasePromotionStockCount = promotionStock - tryPromotionCount;
            tryPurchaseStockCount = buyCount - promotionStock;
            tryAddPurchaseCount = 0;
            tryAddPromotionCount = 0;
        }
    }

    private void buyNoPromotion() {
        if(promotionStock < purchaseProduct + promotionProduct && totalStock >= buyCount) {
            tryPromotionCount = 0;
            tryPurchasePromotionStockCount = 0;
            tryPurchaseStockCount = buyCount;
            tryAddPurchaseCount = 0;
            tryAddPromotionCount = 0;
        }
    }

    private void outOfStock() {
        if(totalStock < buyCount) {
            throw new OutOfStockException(재고_부족.getMessage());
        }
    }
}
