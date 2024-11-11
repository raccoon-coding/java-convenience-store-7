package store.dto;

import store.domain.PurchaseProduct;
import store.domain.Receipt;

import java.util.List;

public class ReceiptDto {
    private static final String PREFIX = "===========W 편의점=============\n상품명\t\t수량\t금액\n";
    private static final String PROMOTION = "===========증\t정=============\n";
    private static final String TOTAL = "==============================\n";

    private String totalReceipt;

    public ReceiptDto(Receipt receipt) {
        transformReceipt(receipt);
    }

    public String getTotalReceipt() {
        return totalReceipt;
    }

    private void transformReceipt(Receipt receipt) {
        StringBuilder purchase = new StringBuilder(PREFIX);
        StringBuilder promotion = new StringBuilder(PROMOTION);
        StringBuilder amount = new StringBuilder(TOTAL);
        transformProducts(receipt, purchase, promotion);
        transformTotalAmount(receipt, amount);

        this.totalReceipt = purchase.toString() + promotion + amount;
    }

    private void transformProducts(Receipt receipt, StringBuilder purchase, StringBuilder promotion) {
        List<PurchaseProduct> products = receipt.getReceipt();
        products.forEach(product -> {
            transformPurchase(product, purchase);
            transformPromotion(product, promotion);
        });
    }

    private void transformPurchase(PurchaseProduct product, StringBuilder sb) {
        sb.append(String.format("%s\t%d\t%,d\n",
                product.getProductName(), product.getTotalPurchaseCount(), product.getTotalPurchaseAmount()));
    }

    private void transformPromotion(PurchaseProduct product, StringBuilder sb) {
        if(product.isPromotionProduct()) {
            sb.append(String.format("%s\t%d\n", product.getProductName(), product.getPromotionCount()));
        }
    }

    private void transformTotalAmount(Receipt receipt, StringBuilder sb) {
        Integer totalAmount = receipt.getTotalAmount();
        Integer promotionAmount = receipt.getPromotionAmount();
        Integer membershipDiscount = isAgreeMembershipDiscount(receipt, totalAmount, promotionAmount);

        sb.append(String.format("%s\t%d\t%,d\n", "총구매액", receipt.getTotalCount(), totalAmount));
        sb.append(String.format("%s\t\t-%,d\n", "행사할인", promotionAmount));
        sb.append(String.format("%s\t\t-%,d\n", "멤버십할인", membershipDiscount));
        sb.append(String.format("%s\t\t %,d\n", "내실돈", totalAmount - promotionAmount - membershipDiscount));
    }

    private Integer isAgreeMembershipDiscount(Receipt receipt, Integer totalAmount, Integer promotionAmount) {
        if(receipt.getMembershipDiscount()) {
            return (totalAmount - promotionAmount) / 10 * 3;
        }
        return 0;
    }
}
