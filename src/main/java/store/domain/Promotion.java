package store.domain;

import java.time.LocalDateTime;

public enum Promotion {
    할인없음("할인없음", 0, 0, LocalDateTime.MIN, LocalDateTime.MAX),
    탄산할인("탄산2+1", 2, 1, makeDate(2024, 1, 1), makeDate(2024, 12, 31)),
    MD추천상품("MD추천상품", 1, 1, makeDate(2024, 1, 1), makeDate(2024, 12, 31)),
    반짝할인("반짝할인",1,1,makeDate(2024, 11, 1), makeDate(2024, 11, 30));

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

    public Integer getBuy() {
        return buy;
    }

    public Integer getGet() {
        return get;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    private static LocalDateTime makeDate(int year, int month, int day) {
        return LocalDateTime.of(year,month,day,23,59,59);
    }
}
