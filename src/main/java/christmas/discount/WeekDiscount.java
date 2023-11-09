package christmas.discount;

import christmas.DecemberDate;
import christmas.FoodType;
import christmas.Menus;
import christmas.Money;

public class WeekDiscount {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;

    public Money calculateDiscountAmount(DecemberDate date, Menus menus) {
        return calculateWeekendDiscountAmount(date, menus);
    }

    private Money calculateWeekendDiscountAmount(DecemberDate date, Menus menus) {
        if (date.isWeekend()) {
            return new Money(menus.getCountByFoodType(FoodType.MAIN_COURSE) * WEEKEND_DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }
}
