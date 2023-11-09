package christmas;

public record DecemberDate(int dateAmount) {
    // TODO dateAmount 를 Enum 으로 받아 주말, 평일 여부 판단
    public boolean isLessThan(DecemberDate date) {
        return dateAmount < date.dateAmount();
    }

    public boolean isMoreThan(DecemberDate date) {
        return dateAmount <= date.dateAmount();
    }
}
