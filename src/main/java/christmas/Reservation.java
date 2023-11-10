package christmas;

import christmas.discount.DDayDiscount;
import christmas.discount.SpecialDiscount;
import christmas.discount.WeekDiscount;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    public static final Money MIN_PRESENTATION_MONEY = new Money(120000);
    private DecemberDate decemberDate;
    private Menus menus;

    public Reservation(DecemberDate decemberDate, Menus menus) {
        this.decemberDate = decemberDate;
        this.menus = menus;
    }

    public Money calculateTotalDiscountMoney() {
        Money totalMoney = calculateTotalMoney();
        if (totalMoney.isLessThan(new Money(10000))) {
            return new Money(0);
        }
        List<Money> discountMoneys = new ArrayList<>(List.of(
                DDayDiscount.calculateDiscountAmount(decemberDate),
                WeekDiscount.calculateDiscountAmount(decemberDate, menus),
                SpecialDiscount.calculateDiscountAmount(decemberDate)
        ));
        handleGift(discountMoneys);
        return discountMoneys.stream()
                .reduce(Money::sum)
                .orElse(new Money(0));
    }

    private void handleGift(List<Money> discountMoneys) {
        Money totalMoney = calculateTotalMoney();
        if (totalMoney.isMoreOrEqualThan(MIN_PRESENTATION_MONEY)) {
            discountMoneys.add(Menu.CHAMPAGNE.getPrice());
        }
    }

    public Money calculateTotalMoney() {
        return menus.calculateTotalPrice();
    }

    public Money calculateTotalDiscountedMoney() {
        return calculateTotalMoney().minus(calculateTotalDiscountMoney());
    }
}
