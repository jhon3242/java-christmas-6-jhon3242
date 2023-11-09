package christmas;

public enum Menu {
    MUSHROOM_CREAM_SOUP("양송이수프", FoodType.APPETIZER, new Money(6000)),
    TAPAS("타파스", FoodType.APPETIZER, new Money(5500)),
    CAESAR_SALAD("시저샐러드", FoodType.APPETIZER, new Money(8000)),
    T_BORN_STAKE("티본스테이크", FoodType.MAIN_COURSE, new Money(55000)),
    BBQ_RIBS("바베큐립", FoodType.MAIN_COURSE, new Money(54000)),
    SEA_FOOD_PASTA("해물파스타", FoodType.MAIN_COURSE, new Money(35000)),
    CHRISTMAS_PASTA("크리스마스파스타", FoodType.MAIN_COURSE, new Money(25000)),
    CHOCO_CAKE("초콜릿케이크", FoodType.DESSERT, new Money(15000)),
    ICE_CREAM("아이스크림", FoodType.DESSERT, new Money(5000)),
    ZERO_COKE("제로콜라", FoodType.DRINK, new Money(3000)),
    RED_WINE("레드와인", FoodType.DRINK, new Money(60000)),
    CHAMPAGNE("샴페인", FoodType.DRINK, new Money(250000))
    ;
    private final String name;
    private final FoodType foodType;
    private final Money price;

    Menu(String name, FoodType foodType, Money price) {
        this.name = name;
        this.foodType = foodType;
        this.price = price;
    }

    public boolean isSameType(FoodType foodType) {
        return this.foodType == foodType;
    }
}
