package store.exception;

public enum DomainException {
    재고_부족("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    주문하지_않은_물픔("주문서에 들어가 있지 않는 물품 이름입니다."),
    존재하지_않는_상품("존재하지 않는 상품입니다. 다시 입력해 주세요.");
    private final String message;

    DomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
