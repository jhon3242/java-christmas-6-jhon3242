package christmas.domain.discount;

import christmas.domain.DecemberDate;
import christmas.domain.Money;

public class DDayDiscount {
    public static final String NAME = "크리스마스 디데이 할인";
    private static final DecemberDate START_DATE = new DecemberDate(1);
    private static final DecemberDate END_DATE = new DecemberDate(25);
    private static final int DISCOUNT_AMOUNT = 1000;

    public static Money calculateDiscountAmount(DecemberDate reserveDate) {
        if (isValidateDate(reserveDate)) {
            return new Money(DISCOUNT_AMOUNT + calculateAdditionalAmount(reserveDate));
        }
        return new Money(0);
    }

    private static boolean isValidateDate(DecemberDate date) {
        return date.isLessThan(START_DATE) || date.isMoreThan(END_DATE);
    }

    private static int calculateAdditionalAmount(DecemberDate date) {
        return (date.dateAmount() - START_DATE.dateAmount()) * 100;
    }
}
