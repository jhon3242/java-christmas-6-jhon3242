package christmas.domain.discount;

import christmas.domain.DecemberDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;
import christmas.domain.Money;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DiscountRepository {
    private static final Money MIN_DISCOUNT_MONEY = new Money(10000);
    private final Map<String, Money> discountRepository;

    private DiscountRepository(DecemberDate date, OrderRepository menus) {
        Map<String, Money> repository = new LinkedHashMap<>();
        initDiscountRepository(date, menus, repository);
        this.discountRepository = repository;
    }

    public static DiscountRepository of(DecemberDate date, OrderRepository orderRepository) {
        return new DiscountRepository(date, orderRepository);
    }

    private void initDiscountRepository(DecemberDate date, OrderRepository menus, Map<String, Money> discountResult) {
        Money totalPrice = menus.calculateTotalPrice();
        if (totalPrice.isMoreOrEqualThan(MIN_DISCOUNT_MONEY)) {
            discountResult.put(DDayDiscount.NAME, DDayDiscount.calculateDiscountAmount(date));
            discountResult.put(WeekDiscount.getNameByDate(date), WeekDiscount.calculateDiscountAmount(date, menus));
            discountResult.put(SpecialDiscount.NAME, SpecialDiscount.calculateDiscountAmount(date));
            discountResult.put(GiftDiscount.NAME, GiftDiscount.calculateDiscountAmount(totalPrice));
        }
    }

    public Money calculateTotalDiscountMoney() {
        return discountRepository.values()
                .stream()
                .reduce(new Money(0), Money::sum);
    }

    public Menu calculateGift() {
        Money money = discountRepository.get(GiftDiscount.NAME);
        if (Objects.isNull(money) || money.isZero()) {
            return null;
        }
        return GiftDiscount.getGift();
    }

    public boolean isNoDiscount() {
        return discountRepository.isEmpty() || calculateTotalDiscountMoney().isZero();
    }

    public boolean hasGiftDiscount() {
        return discountRepository.containsKey(GiftDiscount.NAME) &&
                !discountRepository.get(GiftDiscount.NAME).isZero();
    }

    public Map<String, Money> getDiscountRepository() {
        return Collections.unmodifiableMap(discountRepository);
    }
}

