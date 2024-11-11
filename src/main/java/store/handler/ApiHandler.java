package store.handler;

import store.handler.message.ServerMessage;
import store.handler.message.dto.RequestPurchaseProducts;
import store.handler.message.dto.RequestUserAgree;

import java.util.Map;

public class ApiHandler {
    private final Validator validator;

    public ApiHandler(Validator validator) {
        this.validator = validator;
    }

    public Api<RequestPurchaseProducts> transformProductsDto(String input) {
        Map<String, Integer> products = validator.purchaseProducts(input);
        return new Api<>(ServerMessage.클라이언트_성공, new RequestPurchaseProducts(products));
    }

    public Api<RequestUserAgree> transformUserAgree(String input) {
        Boolean agreement = validator.getUserAgreement(input.trim());
        return new Api<>(ServerMessage.클라이언트_성공, new RequestUserAgree(agreement));
    }
}
