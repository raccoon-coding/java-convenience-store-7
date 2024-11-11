package store.handler;

import store.dto.GetPromotionDto;
import store.dto.NotPromotionDto;
import store.dto.ReceiptDto;
import store.handler.message.dto.RequestPurchaseProducts;
import store.handler.message.dto.RequestUserAgree;
import store.util.CallBackTemplate;
import store.view.Input;
import store.view.Output;
import store.view.ViewConstants;

public class ViewHandler {
    private final CallBackTemplate retry;
    private final ApiHandler handler;
    private final Input input;
    private final Output output;

    public ViewHandler(CallBackTemplate retry, ApiHandler handler, Input input, Output output) {
        this.retry = retry;
        this.handler = handler;
        this.input = input;
        this.output = output;
    }

    public void viewErrorMessage(String message) {
        output.viewExceptionMessage(message);
    }

    public void viewTotalPurchase(Api<ReceiptDto> api) {
        String receipt = api.getData().getTotalReceipt();
        output.viewReceipt(receipt);
    }

    public Api<RequestPurchaseProducts> requestPurchaseProducts(String products) {
        return retry.retryArgument(() -> {
            String request = input.getPurchaseProducts(products);
            return handler.transformProductsDto(request);
        }, e -> viewErrorMessage(e.getMessage())
        );
    }

    public Api<RequestUserAgree> requestPromotion(GetPromotionDto dto) {
        return retry.retryArgument(() -> {
            String viewFormat = String.format(ViewConstants.프로모션_추가_구매.getMessage(),
                    dto.getProductName(), dto.getPromotionCount());
            String agreement = input.getPromotionProducts(viewFormat);
            return handler.transformUserAgree(agreement);
        }, e -> viewErrorMessage(e.getMessage()));
    }

    public Api<RequestUserAgree> requestNotPromotion(NotPromotionDto dto) {
        return retry.retryArgument(() -> {
            String viewFormat = String.format(ViewConstants.프로모션_적용_불가.getMessage(),
                    dto.getProductName(), dto.getProductCount());
            String agreement = input.haveNotPromotionProducts(viewFormat);
            return handler.transformUserAgree(agreement);
        }, e -> viewErrorMessage(e.getMessage()));
    }

    public Api<RequestUserAgree> requestDiscount() {
        return retry.retryArgument(() -> {
            String agreement = input.getMembershipDiscount();
            return handler.transformUserAgree(agreement);
        }, e -> viewErrorMessage(e.getMessage()));
    }

    public Api<RequestUserAgree> requestMorePurchase() {
        return retry.retryArgument(() -> {
            String agreement = input.purchaseMoreProduct();
            return handler.transformUserAgree(agreement);
        }, e -> viewErrorMessage(e.getMessage()));
    }
}
