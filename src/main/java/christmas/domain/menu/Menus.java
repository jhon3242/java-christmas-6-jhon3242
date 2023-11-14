package christmas.domain.menu;

import christmas.domain.Money;
import java.util.Collections;
import java.util.Map;

public record Menus(Map<Menu, Integer> menuRepository) {

    public static Menus createByString(String menuString) {
        Map<Menu, Integer> menuRepository = MenusParser.parse(menuString);
        return new Menus(menuRepository);
    }

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
                .map((menu) -> menu.getPrice().multiply(menuRepository.get(menu)))
                .reduce(new Money(0), Money::sum);
    }

    @Override
    public Map<Menu, Integer> menuRepository() {
        return Collections.unmodifiableMap(menuRepository);
    }
}
