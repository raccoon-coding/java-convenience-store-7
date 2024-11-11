package store.controller;

import store.domain.Product;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.PurchaseProduct;
import store.domain.Receipt;
import store.dto.ProductsDto;
import store.dto.ReceiptDto;
import store.handler.Api;
import store.handler.message.dto.RequestUserAgree;

import java.util.List;
import java.util.Map;

public class ConvenienceController {
    private final Products products;
    private final Promotions promotions;

    public ConvenienceController(Products products, Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public ProductsDto getProducts() {
        List<Product> list = products.getProducts();
        return new ProductsDto(list);
    }

    public Receipt tryProducts(Map<String, Integer> input) {
        return new Receipt(input, products);
    }

    public List<PurchaseProduct> findAddPromotionProduct(Receipt receipt) {
        return receipt.findAddPromotion();
    }

    public void addPromotionProduct(Api<RequestUserAgree> api, PurchaseProduct product) {
        if(api.getData().getAgree()) {
            product.addPromotionCount();
        }
    }

    public List<PurchaseProduct> findNotPromotion(Receipt receipt) {
        return receipt.findNotPromotion();
    }

    public void addNotPromotionProduct(Api<RequestUserAgree> api, PurchaseProduct product) {
        if(api.getData().getAgree()) {
            product.addPurchasePromotionCount();
        }
    }

    public void discountMembership(Api<RequestUserAgree> api, Receipt receipt) {
        receipt.requestMembershipDiscount(api.getData().getAgree());
    }

    public ReceiptDto buyProducts(Receipt receipt) {
        receipt.purchaseProducts();
        return new ReceiptDto(receipt);
    }
}
