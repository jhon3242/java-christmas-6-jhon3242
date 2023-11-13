package christmas;

import christmas.discount.DiscountRepository;
import christmas.discount.GiftDiscount;

public class Reservation {

    private DiscountRepository discountRepository;
    private DecemberDate decemberDate;
    private Menus menus;

    private Reservation(DecemberDate decemberDate, Menus menus) {
        this.decemberDate = decemberDate;
        this.menus = menus;
        this.discountRepository = DiscountRepository.calculateDiscount(decemberDate, menus);
    }

    public static Reservation of(DecemberDate decemberDate, Menus menus) {
        return new Reservation(decemberDate, menus);
    }

    public Menu calculateGift() {
        return discountRepository.calculateGift();
    }

    public Money calculateTotalDiscountMoney() {
        return discountRepository.calculateTotalDiscountMoney();
    }

    public Money calculateTotalMoney() {
        return menus.calculateTotalPrice();
    }

    public Money calculateTotalDiscountedMoney() {
        Money discountMoney = calculateTotalDiscountMoney();
        if (discountRepository.hasGiftDiscount()) {
            discountMoney = discountMoney.minus(GiftDiscount.getGift().getPrice());
        }
        return calculateTotalMoney().minus(discountMoney);
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

    public DiscountRepository getDiscountRepository() {
        return discountRepository;
    }
}
