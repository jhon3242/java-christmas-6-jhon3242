package christmas.discount;

import christmas.DecemberDate;
import christmas.FoodType;
import christmas.Menus;
import christmas.Money;

public class WeekDiscount {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;

    public Money calculateDiscountAmount(DecemberDate date, Menus menus) {
        Money weekendDiscountAmount = calculateWeekendDiscountAmount(date, menus);
        Money weekdayDiscountAmount = calculateWeekdayDiscountAmount(date, menus);
        return Money.sum(weekendDiscountAmount, weekdayDiscountAmount);
    }

    private Money calculateWeekendDiscountAmount(DecemberDate date, Menus menus) {
        if (date.isWeekend()) {
            return new Money(menus.findTotalCountByFoodType(FoodType.MAIN_COURSE) * WEEKEND_DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }

    private Money calculateWeekdayDiscountAmount(DecemberDate date, Menus menus) {
        if (date.isWeekday()) {
            return new Money(menus.findTotalCountByFoodType(FoodType.DESSERT) * WEEKEND_DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }
}
