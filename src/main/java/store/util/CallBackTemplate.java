package store.util;

import store.exception.MyIllegalArgumentException;
import store.exception.MyIllegalStateException;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CallBackTemplate {
    public CallBackTemplate() {
    }

    public <T> T retryArgument(Supplier<T> callback, Consumer<MyIllegalArgumentException> exceptionHandler) {
        while (true) {
            try {
                return callback.get();
            } catch (MyIllegalArgumentException e) {
                exceptionHandler.accept(e);
            }
        }
    }

    public <T> T retryState(Supplier<T> callback, Consumer<MyIllegalStateException> exceptionHandler) {
        while (true) {
            try {
                return callback.get();
            } catch (MyIllegalStateException e) {
                exceptionHandler.accept(e);
            }
        }
    }
}
