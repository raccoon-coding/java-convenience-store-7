package store.domain;

import store.exception.NotOrderProduct;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static store.exception.DomainException.주문하지_않은_물픔;

public class Receipt {
    private final List<PurchaseProduct> receipt;

    private Boolean membershipDiscount;

    public Receipt(Map<String, Integer> input, Products products) {
        this.receipt = construct(input, products);
    }

    public Integer getTotalCount() {
        return receipt.stream().mapToInt(PurchaseProduct::getTotalPurchaseCount).sum();
    }

    public Integer getTotalAmount() {
        return receipt.stream().mapToInt(PurchaseProduct::getTotalPurchaseAmount).sum();
    }

    public Integer getPromotionAmount() {
        return receipt.stream().mapToInt(PurchaseProduct::getPromotionAmount).sum();
    }

    public void purchaseProducts() {
        receipt.forEach(PurchaseProduct::purchase);
    }

    public Boolean getMembershipDiscount() {
        return this.membershipDiscount;
    }

    public List<PurchaseProduct> findNotPromotion() {
        return receipt.stream()
                .filter(PurchaseProduct::isNotPromotion)
                .toList();
    }

    public List<PurchaseProduct> findAddPromotion() {
        return receipt.stream()
                .filter(PurchaseProduct::isAddBuyProduct)
                .toList();
    }

    public void requestMembershipDiscount(Boolean agree) {
        if(agree) {
            this.membershipDiscount = true;
            return;
        }
        this.membershipDiscount = false;
    }

    public List<PurchaseProduct> getReceipt() {
        return receipt;
    }

    public PurchaseProduct findProductName(String name) {
        Optional<PurchaseProduct> optional = receipt.stream()
                .filter(purchaseProduct -> purchaseProduct.getProductName().equals(name))
                .findFirst();
        return verifyPurchaseProduct(optional);
    }

    private PurchaseProduct verifyPurchaseProduct(Optional<PurchaseProduct> optional) {
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotOrderProduct(주문하지_않은_물픔.getMessage());
    }

    private List<PurchaseProduct> construct(Map<String, Integer> purchaseProducts, Products products) {
        return purchaseProducts.entrySet().stream()
                .map(entry -> {
                    Product product = products.findProductByNameNotPromotion(entry.getKey());
                    Product promotion = products.findProductByNamePromotion(entry.getKey());
                    return product.tryPurchasePromotion(entry.getValue(), promotion);
                })
                .collect(Collectors.toList());
    }
}
