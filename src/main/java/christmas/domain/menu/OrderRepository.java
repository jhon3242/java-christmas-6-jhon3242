package christmas.domain.menu;

import christmas.domain.Money;
import java.util.Collections;
import java.util.Map;

public record OrderRepository(Map<Menu, Integer> repository) {
    public static OrderRepository createByString(String orderString) {
        Map<Menu, Integer> orderRepository = OrderParser.parse(orderString);
        return new OrderRepository(orderRepository);
    }

    public int findTotalCountByFoodType(FoodType foodType) {
        return repository.keySet()
                .stream()
                .filter(menu -> menu.isSameType(foodType))
                .map(repository::get)
                .reduce(0, Integer::sum);
    }

    public Money calculateTotalPrice() {
        return repository.keySet()
                .stream()
                .map((menu) -> menu.getPrice().multiply(repository.get(menu)))
                .reduce(new Money(0), Money::sum);
    }

    public Map<Menu, Integer> repository() {
        return Collections.unmodifiableMap(repository);
    }
}
