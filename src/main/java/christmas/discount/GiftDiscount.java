package christmas.discount;

import christmas.Menu;
import christmas.Money;
import java.util.Objects;

public class GiftDiscount {
    public static final String NAME = "증정 이벤트";
    private static final Money MIN_PRESENTATION_MONEY = new Money(120000);

    public static Money calculateDiscountAmount(Money totalPrize) {
        if (totalPrize.isMoreOrEqualThan(MIN_PRESENTATION_MONEY)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return null;
    }

    public static Menu getGift() {
        return Menu.CHAMPAGNE;
    }
}