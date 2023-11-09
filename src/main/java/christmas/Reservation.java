package christmas;

import christmas.discount.DDayDiscount;
import christmas.discount.SpecialDiscount;
import christmas.discount.WeekDiscount;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    public static final Money MIN_PRESENTATION_MONEY = new Money(120000);
    private Menus menus;
    private DecemberDate decemberDate;

    public Reservation(Menus menus, DecemberDate decemberDate) {
        this.menus = menus;
        this.decemberDate = decemberDate;
    }

    public Money calculateTotalDiscountMoney() {
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
        return null;
    }
}
