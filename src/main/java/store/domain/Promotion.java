package store.domain;

import store.domain.dto.PromotionDto;

import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final Integer buy;
    private final Integer get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Promotion(String name, Integer buy, Integer get, LocalDateTime startDate, LocalDateTime endDate) {
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

    public PromotionDto tryPurchaseProduct() {
        return new PromotionDto(buy, get);
    }

    private static LocalDateTime makeDate(int year, int month, int day) {
        return LocalDateTime.of(year,month,day,23,59,59);
    }
}
