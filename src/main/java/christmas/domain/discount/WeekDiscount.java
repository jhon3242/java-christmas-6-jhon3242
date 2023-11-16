package christmas.domain.discount;

import christmas.domain.DecemberDate;
import christmas.domain.menu.FoodType;
import christmas.domain.menu.OrderRepository;
import christmas.domain.Money;

public class WeekDiscount {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;

    public static Money calculateDiscountAmount(DecemberDate date, OrderRepository menus) {
        Money weekendDiscountAmount = calculateWeekendDiscountAmount(date, menus);
        Money weekdayDiscountAmount = calculateWeekdayDiscountAmount(date, menus);
        return Money.sum(weekendDiscountAmount, weekdayDiscountAmount);
    }

    private static Money calculateWeekendDiscountAmount(DecemberDate date, OrderRepository menus) {
        if (date.isWeekend()) {
            return new Money(menus.findTotalCountByFoodType(FoodType.MAIN_COURSE) * WEEKEND_DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }

    private static Money calculateWeekdayDiscountAmount(DecemberDate date, OrderRepository menus) {
        if (date.isWeekday()) {
            return new Money(menus.findTotalCountByFoodType(FoodType.DESSERT) * WEEKEND_DISCOUNT_AMOUNT);
        }
        return new Money(0);
    }

    public static String getNameByDate(DecemberDate date) {
        if (date.isWeekend()) {
            return "주말 할인";
        }
        return "평일 할인";
    }
}
