package store.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public enum Promotion {
    탄산할인("탄산2+1", 2, 1, makeDate(2023, 12, 31), makeDate(2024, 12, 31)),
    MD추천상품("MD추천상품", 1, 1, makeDate(2023, 12, 31), makeDate(2024, 12, 31)),
    반짝할인("반짝할인",1,1,makeDate(2024, 10, 31), makeDate(2024, 11, 30)),
    할인없음("할인없음", 0, 0, LocalDateTime.MIN, LocalDateTime.MAX);

    private final String name;
    private final Integer buy;
    private final Integer get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    Promotion(String name, Integer buy, Integer get, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public Boolean isPromotionDate(LocalDateTime dateTimes) {
        return dateTimes.isAfter(startDate) && dateTimes.isBefore(endDate);
    }

    public Integer tryPurchaseProduct(Integer purchaseProductCount) {
        if(Objects.equals(name, "할인없음")){
            return purchaseProductCount;
        }

        Integer promotionUnits = buy + get;
        return purchaseProductCount - (purchaseProductCount % promotionUnits);
    }

    private static LocalDateTime makeDate(int year, int month, int day) {
        return LocalDateTime.of(year,month,day,23,59,59);
    }
}
