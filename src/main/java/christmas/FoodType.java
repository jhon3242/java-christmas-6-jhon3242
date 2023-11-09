package christmas;

public enum FoodType {
    APPETIZER("애피타이저"),
    DESSERT("디저트"),
    MAIN_COURSE("메인"),
    DRINK("음료");

    private final String name;

    FoodType(String name) {
        this.name = name;
    }
}
