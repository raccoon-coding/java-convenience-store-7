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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Products {
    콜라(new Cola("콜라", 1_000)),
    사이다(new Sprite("사이다",1_000)),
    오렌지주스(new OrangeJuice("오렌지주스", 1_800)),
    탄산수(new SparklingWater("탄산수", 1_200)),
    물(new Water("물", 500)),
    비타민워터(new VitaminWater("비타민워터", 1_500)),
    감자칩(new PotatoChips("감자칩", 1_500)),
    초코바(new ChocolateBar("초코바", 1_200)),
    에너지바(new EnergyBar("에너지바", 2_000)),
    정식도시락(new LunchBox("정식도시락", 6_400)),
    컵라면(new CupRamen("컵라면", 1_700));

    private final Product product;

    Products(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public static Product findProductByName(String productName) {
        return Arrays.stream(Products.values())
                .filter(products -> Objects.equals(products.getProduct().getName(), productName))
                .findFirst().get().product;
    }
}
