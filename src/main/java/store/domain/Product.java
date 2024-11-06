package store.domain;

import static store.domain.Promotion.MD추천상품;
import static store.domain.Promotion.반짝할인;
import static store.domain.Promotion.탄산할인;
import static store.domain.Promotion.할인없음;

public enum Product {
    콜라_프로모션("콜라",1_000, 10, 탄산할인),
    콜라("콜라",1_000, 10,할인없음),
    사이다_프로모션("사이다",1_000, 8, 탄산할인),
    사이다("사이다",1_000, 8,할인없음),
    오렌지주스("오렌지주스", 1_800, 9, MD추천상품),
    탄산수("탄산수", 1_200, 5, 탄산할인),
    물("물", 500, 10, 할인없음),
    비타민워터("비타민워터", 1_500, 6, 할인없음),
    감자칩_프로모션("감자칩", 1_500, 5, 반짝할인),
    감자칩("감자칩", 1_500, 5, 할인없음),
    초코바_프로모션("초코바", 1_200, 5, MD추천상품),
    초코바("초코바", 1_200, 5, 할인없음),
    에너지바("에너지바", 2_000, 5, 할인없음),
    정식도시락("정식도시락", 6_400, 8, 할인없음),
    컵라면_프로모션("컵라면",1_700, 1, MD추천상품),
    컵라면("컵라면", 1_700, 10, 할인없음);

    private final String name;
    private final Integer price;
    private final int quantity;
    private final Promotion promotion;

    Product(String name, Integer price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
