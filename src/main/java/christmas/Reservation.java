package christmas;

import christmas.discount.DDayDiscount;
import christmas.discount.SpecialDiscount;
import christmas.discount.WeekDiscount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    public static final Money MIN_PRESENTATION_MONEY = new Money(120000);
    private static final Money MIN_DISCOUNT_MONEY = new Money(10000);
    private Map<String, Money> discountRepository;
    private DecemberDate decemberDate;
    private Menus menus;

    public Reservation(DecemberDate decemberDate, Menus menus) {
        this.decemberDate = decemberDate;
        this.menus = menus;
    }

    public void calculateDiscount() {
        Map<String, Money> result = new LinkedHashMap<>();
        if (menus.calculateTotalPrice().isMoreOrEqualThan(MIN_DISCOUNT_MONEY)) {
            handleDiscount(result, DDayDiscount.NAME, DDayDiscount.calculateDiscountAmount(decemberDate));
            handleDiscount(result, WeekDiscount.getNameByDate(decemberDate), WeekDiscount.calculateDiscountAmount(decemberDate, menus));
            handleDiscount(result, SpecialDiscount.NAME, SpecialDiscount.calculateDiscountAmount(decemberDate));
            handleDiscount(result, "증정 이벤트", calculateGiftMoney());
        }
        this.discountRepository = result;
    }

    private void handleDiscount(Map<String, Money> map, String eventName, Money discount) {
        if (discount.equals(new Money(0))) {
            return;
        }
        map.put(eventName, discount);
    }

    private Money calculateGiftMoney() {
        Money totalMoney = calculateTotalMoney();
        if (totalMoney.isMoreOrEqualThan(MIN_PRESENTATION_MONEY)) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return new Money(0);
    }

    public Menu calculateGift() {
        Money totalMoney = calculateTotalMoney();
        if (totalMoney.isMoreOrEqualThan(MIN_PRESENTATION_MONEY)) {
            return Menu.CHAMPAGNE;
        }
        return null;
    }

    public Money calculateTotalDiscountMoney() {
        return discountRepository.values()
                .stream()
                .reduce(Money::sum)
                .orElse(new Money(0));
    }

    public Money calculateTotalMoney() {
        return menus.calculateTotalPrice();
    }

    public Money calculateTotalDiscountedMoney() {
        Money discountMoney = calculateTotalDiscountMoney();
        return calculateTotalMoney().minus(discountMoney);
    }

    public Map<String, Money> getDiscountRepository() {
        return Collections.unmodifiableMap(discountRepository);
    }

    // TODO Enum 리팩터링
    public String calculateEventBadge() {
        Money totalDiscountMoney = calculateTotalDiscountMoney();
        if (totalDiscountMoney.isMoreOrEqualThan(new Money(20000))) {
            return "산타";
        }
        if (totalDiscountMoney.isMoreOrEqualThan(new Money(10000))) {
            return "트리";
        }
        if (totalDiscountMoney.isMoreOrEqualThan(new Money(5000))) {
            return "별";
        }
        return "없음";
    }
}
