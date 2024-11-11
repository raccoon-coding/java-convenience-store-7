package store.handler.message.dto;

public class RequestUserAgree {
    private final Boolean agree;

    public RequestUserAgree(Boolean agree) {
        this.agree = agree;
    }

    public Boolean getAgree() {
        return agree;
    }
}
