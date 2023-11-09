package christmas;

import java.util.Map;

public record Menus(Map<Menu, Integer> menuRepository) {

    public int findTotalCountByFoodType(FoodType foodType) {
        return menuRepository.keySet()
                .stream()
                .filter(menu -> menu.isSameType(foodType))
                .map(menuRepository::get)
                .reduce(0, Integer::sum);
    }

    public Money calculateTotalPrice() {
        return menuRepository.keySet()
                .stream()
                .map(Menu::getPrice)
                .reduce(new Money(0), Money::sum);
    }
}
