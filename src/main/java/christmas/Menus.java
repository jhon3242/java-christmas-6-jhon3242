package christmas;

import java.util.Map;

public class Menus {
    private Map<Menu, Integer> menuRepository;

    public Menus(Map<Menu, Integer> menuRepository) {
        this.menuRepository = menuRepository;
    }

    public int findTotalCountByFoodType(FoodType foodType) {
        return menuRepository.keySet()
                .stream()
                .filter(menu -> menu.isSameType(foodType))
                .map(menuRepository::get)
                .reduce(0, Integer::sum);
    }
}
