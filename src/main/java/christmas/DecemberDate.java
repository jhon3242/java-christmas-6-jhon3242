package christmas;

import java.util.List;

public record DecemberDate(int dateAmount) {
    private static final List<Integer> weekendDateAmounts = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

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
