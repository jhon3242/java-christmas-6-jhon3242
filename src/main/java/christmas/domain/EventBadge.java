package christmas.domain;

public enum EventBadge {
    SANTA("산타", new Money( 20000)),
    TREE("트리", new Money( 10000)),
    STAR("별", new Money(5000)),
    NONE("없음", new Money(0));

    private final String name;
    private final Money price;

    EventBadge(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public static EventBadge findByTotalDiscountPrice(Money totalDiscountMoney) {
        if (totalDiscountMoney.isMoreOrEqualThan(SANTA.price)) {
            return SANTA;
        }
        if (totalDiscountMoney.isMoreOrEqualThan(TREE.price)) {
            return TREE;
        }
        if (totalDiscountMoney.isMoreOrEqualThan(STAR.price)) {
            return STAR;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
