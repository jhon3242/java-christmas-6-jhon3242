package christmas;

public record DecemberDate(int dateAmount) {
    public boolean isLessThan(DecemberDate date) {
        return dateAmount < date.dateAmount();
    }

    public boolean isMoreThan(DecemberDate date) {
        return dateAmount <= date.dateAmount();
    }
}
