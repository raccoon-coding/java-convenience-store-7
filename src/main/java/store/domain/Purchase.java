package store.domain;

import store.domain.dto.ProductStockDto;
import store.domain.dto.PurchaseCountDto;

public class Purchase {
    private final Integer purchaseCount;
    private final Product defaultProduct;
    private final Product promotionProduct;
    private final Integer buyCount;
    private final Integer getCount;

    private Integer tryPurchaseStockCount;
    private Integer tryPurchasePromotionStockCount;
    private Integer tryPromotionCount;
    private Integer tryAddPromotionCount;

    public Purchase(ProductStockDto dto) {
        this.purchaseCount = dto.purchaseCount();
        this.defaultProduct = dto.defaultProduct();
        this.promotionProduct = dto.promotionProduct();
        this.buyCount = dto.buyCount();
        this.getCount = dto.getCount();
    }

    public PurchaseCountDto countPromotionProduct() {
        promotionProduct();
        outOfStockNotPromotion();
        stockNotPromotion();
        return new PurchaseCountDto(tryPurchaseStockCount, tryPurchasePromotionStockCount,
                tryPromotionCount, tryAddPromotionCount);
    }

    private void promotionProduct() {
        if(promotionProduct != null && getCount != 0) {
            buyPromotion();
            buyPortionPromotion();
            buyNoPromotion();
            outOfStock();
        }
    }


    private void buyPromotion() {
        if(promotionProduct.getStock() >= purchaseCount) {
            tryPromotionCount = purchaseCount / (buyCount + getCount);
            tryPurchaseStockCount = purchaseCount - tryPromotionCount;
            tryPurchasePromotionStockCount = 0;
            isPromotion();
        }
    }

    private void isPromotion() {
        if(purchaseCount % (buyCount + getCount) == buyCount) {
            tryAddPromotionCount = 1;
            return;
        }
        tryAddPromotionCount = 0;
    }

    private void buyPortionPromotion() {
        if(promotionProduct.getStock() >= buyCount + getCount && promotionProduct.getStock() < purchaseCount) {
            tryPromotionCount = promotionProduct.getStock() / (buyCount + getCount);
            tryPurchasePromotionStockCount = promotionProduct.getStock() % (buyCount + getCount);
            tryPurchaseStockCount = purchaseCount - tryPromotionCount;
            tryAddPromotionCount = 0;
        }
    }

    private void buyNoPromotion() {
        if(promotionProduct.getStock() < buyCount + getCount &&
                promotionProduct.getStock() + defaultProduct.getStock() >= purchaseCount) {
            tryPromotionCount = 0;
            tryPurchasePromotionStockCount = 0;
            tryPurchaseStockCount = purchaseCount;
            tryAddPromotionCount = 0;
        }
    }

    private void outOfStock() {
        if(promotionProduct.getStock() + defaultProduct.getStock() < purchaseCount) {
            tryPromotionCount = 0;
            tryPurchaseStockCount = -1;
            tryPurchasePromotionStockCount = -1;
            tryAddPromotionCount = 0;
        }
    }

    private void outOfStockNotPromotion() {
        if(defaultProduct.getStock() < purchaseCount) {
            tryPromotionCount = 0;
            tryPurchaseStockCount = -1;
            tryPurchasePromotionStockCount = -1;
            tryAddPromotionCount = 0;
        }
    }

    private void stockNotPromotion() {
        if(defaultProduct.getStock() >= purchaseCount) {
            tryPromotionCount = 0;
            tryPurchasePromotionStockCount = 0;
            tryPurchaseStockCount = purchaseCount;
            tryAddPromotionCount = 0;
        }
    }
}
