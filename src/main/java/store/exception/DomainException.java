package store.exception;

public enum DomainException {
    재고_부족("재고를 초과하는 수량입니다. 다시 입력해주세요.");
    private final String message;

    DomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[Error] " + message;
    }
}
