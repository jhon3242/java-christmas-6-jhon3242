package christmas.domain.menu;

import christmas.domain.Money;
import java.util.Collections;
import java.util.Map;

public record OrderRepository(Map<Menu, Integer> menuRepository) {

    public static OrderRepository createByString(String menuString) {
        Map<Menu, Integer> menuRepository = OrderParser.parse(menuString);
        return new OrderRepository(menuRepository);
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
