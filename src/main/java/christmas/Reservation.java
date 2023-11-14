package christmas;

import christmas.discount.DiscountRepository;
import christmas.discount.GiftDiscount;

public class Reservation {

    private final DiscountRepository discountRepository;
    private final DecemberDate decemberDate;
    private final Menus menus;

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

    public Money calculateTotalOriginalMoney() {
        return menus.calculateTotalPrice();
    }

    public Money calculateTotalDiscountMoney() {
        return discountRepository.calculateTotalDiscountMoney();
    }

    public Money calculateTotalFinalMoney() {
        Money discountMoney = calculateTotalDiscountMoney();
        if (discountRepository.hasGiftDiscount()) {
            discountMoney = discountMoney.minus(GiftDiscount.getGift().getPrice());
        }
        return calculateTotalOriginalMoney().minus(discountMoney);
    }

    public EventBadge calculateEventBadge() {
        Money totalDiscountMoney = calculateTotalDiscountMoney();
        return EventBadge.findByTotalDiscountPrice(totalDiscountMoney);
    }

    public DiscountRepository getDiscountRepository() {
        return discountRepository;
    }

    public DecemberDate getDecemberDate() {
        return decemberDate;
    }

    public Menus getMenus() {
        return menus;
    }
}
