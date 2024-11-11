package store;

import store.controller.ConvenienceController;
import store.domain.PurchaseProduct;
import store.domain.Receipt;
import store.dto.GetPromotionDto;
import store.dto.NotPromotionDto;
import store.dto.ProductsDto;
import store.dto.ReceiptDto;
import store.exception.MyIllegalStateException;
import store.handler.Api;
import store.handler.ViewHandler;
import store.handler.message.ServerMessage;
import store.handler.message.dto.RequestPurchaseProducts;
import store.handler.message.dto.RequestUserAgree;
import store.util.CallBackTemplate;

import java.util.List;

public class UserRequest {
    private final CallBackTemplate retry;
    private final ConvenienceController controller;
    private final ViewHandler handler;

    public UserRequest(CallBackTemplate retry, ConvenienceController controller, ViewHandler handler) {
        this.retry = retry;
        this.controller = controller;
        this.handler = handler;
    }

    public void request() {
        Boolean userAccept = true;
        while(userAccept) {
            purchase();
            Api<RequestUserAgree> api = handler.requestMorePurchase();
            userAccept = api.getData().getAgree();
        }
    }

    private void purchase() {
        try{
            logic();
        } catch (MyIllegalStateException e) {
            handler.viewErrorMessage(e.getMessage());
        }
    }

    private void logic() {
        Receipt userReceipt = purchaseConvenience();
        getAgreePromotion(userReceipt);
        getAgreeNotPromotion(userReceipt);
        getMembershipDiscount(userReceipt);
        buyProducts(userReceipt);
    }

    private Receipt purchaseConvenience() {
        return retry.retryState(() -> {
            ProductsDto products = controller.getProducts();
            Api<RequestPurchaseProducts> api = handler.requestPurchaseProducts(products.getProducts());
            return controller.tryProducts(api.getData().getProducts());
        }, e-> handler.viewErrorMessage(e.getMessage())
        );
    }

    private void getAgreePromotion(Receipt receipt) {
        List<PurchaseProduct> products = controller.findAddPromotionProduct(receipt);
        products.forEach(product -> {
            GetPromotionDto dto = new GetPromotionDto(product);
            Api<RequestUserAgree> api = handler.requestPromotion(dto);
            controller.addPromotionProduct(api, product);
        });
    }

    private void getAgreeNotPromotion(Receipt receipt) {
        List<PurchaseProduct> products = controller.findNotPromotion(receipt);
        products.forEach(product -> {
            NotPromotionDto dto = new NotPromotionDto(product);
            Api<RequestUserAgree> api = handler.requestNotPromotion(dto);
            controller.addNotPromotionProduct(api, product);
        });
    }

    private void getMembershipDiscount(Receipt receipt) {
        Api<RequestUserAgree> api = handler.requestDiscount();
        controller.discountMembership(api, receipt);
    }

    private void buyProducts(Receipt receipt) {
        ReceiptDto receiptDto = controller.buyProducts(receipt);
        handler.viewTotalPurchase(new Api<>(ServerMessage.서버_성공, receiptDto));
    }
}
