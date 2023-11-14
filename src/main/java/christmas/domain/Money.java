package christmas.domain;

import christmas.message.ExceptionMessage;

public record Money(int amount) {

    public Money {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONEY);
        }
    }

    public static Money sum(Money money1, Money money2) {
        return new Money(money1.amount + money2.amount);
    }

    public boolean isMoreOrEqualThan(Money source) {
        return amount >= source.amount;
    }

    public Money minus(Money money) {
        return new Money(amount - money.amount);
    }

    public Money multiply(int count) {
        return new Money(amount * count);
    }

    public boolean isZero() {
        return amount == 0;
    }
}
