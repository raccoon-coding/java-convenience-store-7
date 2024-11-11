package store.exception;

public class InvalidPromotion extends MyIllegalStateException {
    public InvalidPromotion(String message) {
        super(message);
    }
}
