package store.view;

public enum ViewConstants {
    구매_요청("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    프로모션_추가_구매("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    추가_구매("현재 %s은(는) %d개를 구매하면 %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    프로모션_적용_불가("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    멤버십_할인("멤버십 할인을 받으시겠습니까? (Y/N)"),
    추가_이용("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    private final String message;

    ViewConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
