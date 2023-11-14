package christmas.domain;

import christmas.domain.discount.DiscountRepository;
import christmas.domain.discount.GiftDiscount;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;

public class Reservation {

    private final DiscountRepository discountRepository;
    private final DecemberDate decemberDate;
    private final OrderRepository menus;

    private Reservation(DecemberDate decemberDate, OrderRepository menus) {
        this.decemberDate = decemberDate;
        this.menus = menus;
        this.discountRepository = DiscountRepository.calculateDiscount(decemberDate, menus);
    }

    public static Reservation of(DecemberDate decemberDate, OrderRepository menus) {
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

    public OrderRepository getMenus() {
        return menus;
    }
}
