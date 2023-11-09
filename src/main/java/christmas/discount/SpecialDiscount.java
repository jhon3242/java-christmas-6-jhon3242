package christmas.discount;

import christmas.DecemberDate;
import christmas.Money;
import java.util.List;

public class SpecialDiscount {
    private static final int DISCOUNT_AMOUNT = 1000;
    private static final List<DecemberDate> specialDates = List.of(
        new DecemberDate(3),
        new DecemberDate(10),
        new DecemberDate(17),
        new DecemberDate(24),
        new DecemberDate(25),
        new DecemberDate(31)
    );

    public Money calculateDiscountAmount(DecemberDate date) {
        if (isSpecialDate(date)) {
            return new Money(DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }

    private boolean isSpecialDate(DecemberDate date) {
        return specialDates.contains(date);
    }
}
