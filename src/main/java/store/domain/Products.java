package store.domain;

import store.domain.product.ChocolateBar;
import store.domain.product.Cola;
import store.domain.product.CupRamen;
import store.domain.product.EnergyBar;
import store.domain.product.LunchBox;
import store.domain.product.OrangeJuice;
import store.domain.product.PotatoChips;
import store.domain.product.SparklingWater;
import store.domain.product.Sprite;
import store.domain.product.VitaminWater;
import store.domain.product.Water;
import store.exception.NotContainProduct;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static store.domain.Promotion.MD추천상품;
import static store.domain.Promotion.반짝할인;
import static store.domain.Promotion.탄산할인;
import static store.domain.Promotion.할인없음;
import static store.exception.DomainException.존재하지_않는_상품;

public enum Products {
    콜라(new Cola("콜라", 1_000, 10, 탄산할인, 10)),
    사이다(new Sprite("사이다",1_000, 7, 탄산할인, 8)),
    오렌지주스(new OrangeJuice("오렌지주스", 1_800, 0, MD추천상품, 9)),
    탄산수(new SparklingWater("탄산수", 1_200, 0, 탄산할인, 5)),
    물(new Water("물", 500, 10, 할인없음, 0)),
    비타민워터(new VitaminWater("비타민워터", 1_500, 6,할인없음, 0)),
    감자칩(new PotatoChips("감자칩", 1_500, 5, 반짝할인, 5)),
    초코바(new ChocolateBar("초코바", 1_200, 5, MD추천상품, 5)),
    에너지바(new EnergyBar("에너지바", 2_000, 5, 할인없음, 0)),
    정식도시락(new LunchBox("정식도시락", 6_400,8, 할인없음, 0)),
    컵라면(new CupRamen("컵라면", 1_700, 10, MD추천상품, 1));

    private final Product product;

    Products(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public static Product findProductByName(String productName) {
        Optional<Products> optional = Arrays.stream(Products.values())
                .filter(products -> Objects.equals(products.getProduct().getName(), productName))
                .findFirst();
        return verifyProduct(optional);

    }
    private static Product verifyProduct(Optional<Products> optional) {
        if(optional.isPresent()) {
            return optional.get().product;
        }
        throw new NotContainProduct(존재하지_않는_상품.getMessage());
    }
}
