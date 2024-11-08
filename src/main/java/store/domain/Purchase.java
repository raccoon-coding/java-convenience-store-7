package store.domain;

import store.domain.dto.ProductStockDto;
import store.domain.dto.PurchaseCountDto;
import store.exception.OutOfStockException;

import static store.exception.DomainException.재고_부족;

public class Purchase {
    private final Integer promotionStock;
    private final Integer buyCount;
    private final Integer totalStock;
    private final Integer purchaseCount;
    private final Integer promotionCount;

    private Integer tryPurchaseCount;
    private Integer tryPromotionCount;
    private Integer tryAddPurchaseCount;

    public Purchase(ProductStockDto dto) {
        this.promotionStock = dto.stock();
        this.buyCount = dto.purchaseCount();
        this.totalStock = dto.totalStock();
        this.purchaseCount = dto.purchaseProduct();
        this.promotionCount = dto.promotionProduct();
    }

    public PurchaseCountDto countPromotionProduct() {
        buyPromotion();
        buyPortionPromotion();
        buyNoPromotion();
        outOfStock();
        return new PurchaseCountDto(tryPurchaseCount, tryPromotionCount, tryAddPurchaseCount);
    }

    private void buyPromotion() {
        if(promotionStock >= purchaseCount + promotionCount && promotionStock >= buyCount) {
            tryPromotionCount = buyCount / (purchaseCount + promotionCount);
            tryPurchaseCount = buyCount - tryPromotionCount;
            tryAddPurchaseCount = purchaseCount + promotionCount - buyCount % (purchaseCount + promotionCount);
        }
    }

    private void buyPortionPromotion() {
        if(promotionStock >= purchaseCount + promotionCount && promotionStock < buyCount) {
            tryPromotionCount = promotionStock / (purchaseCount + promotionCount);
            tryPurchaseCount = buyCount - tryPromotionCount;
            tryAddPurchaseCount = 0;
        }
    }

    private void buyNoPromotion() {
        if(promotionStock < purchaseCount + promotionCount && totalStock >= buyCount) {
            tryPromotionCount = 0;
            tryPurchaseCount = buyCount;
            tryAddPurchaseCount = 0;
        }
    }

    private void outOfStock() {
        if(totalStock < buyCount) {
            throw new OutOfStockException(재고_부족.getMessage());
        }
    }
}
