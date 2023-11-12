package christmas;

import christmas.discount.DiscountRepository;

public class Reservation {

    private DiscountRepository discountRepository;
    private DecemberDate decemberDate;
    private Menus menus;

    public Reservation(DecemberDate decemberDate, Menus menus) {
        this.decemberDate = decemberDate;
        this.menus = menus;
        this.discountRepository = DiscountRepository.calculateDiscount(decemberDate, menus);
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
        return calculateTotalMoney().minus(discountMoney);
    }

//    public Map<String, Money> getDiscountRepository() {
//        return Collections.unmodifiableMap(discountRepository);
//    }

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
