package christmas;

import java.util.List;

public record DecemberDate(int dateAmount) {
    private static final List<Integer> weekendDateAmounts = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final int MINIMUM_DATE_AMOUNT = 1;
    private static final int MAXIMUM_DATE_AMOUNT = 31;

    public DecemberDate {
        validateDate(dateAmount);
    }

    private void validateDate(int dateAmount) {
        if (isOutOfDateRange(dateAmount)) {
            throw new IllegalArgumentException("12월의 날짜를 입력해주세요.");
        }
    }

    private boolean isOutOfDateRange(int dateAmount) {
        return dateAmount < MINIMUM_DATE_AMOUNT || dateAmount > MAXIMUM_DATE_AMOUNT;
    }

    // TODO dateAmount 를 Enum 으로 받아 주말, 평일 여부 판단
    public boolean isLessThan(DecemberDate date) {
        return dateAmount < date.dateAmount();
    }

    public boolean isMoreThan(DecemberDate date) {
        return dateAmount <= date.dateAmount();
    }

    public boolean isWeekend() {
        return weekendDateAmounts.contains(dateAmount);
    }

    public boolean isWeekday() {
        return !isWeekend();
    }
}
