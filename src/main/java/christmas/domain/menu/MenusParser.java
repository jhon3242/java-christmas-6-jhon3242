package christmas.domain.menu;

import christmas.message.ExceptionMessage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class MenusParser {
    private static final int MAX_MENU_COUNT = 20;
    private static final String DELIMITER_MENU_ORDER = ",";

    public static Map<Menu, Integer> parse(String inputValue) {
        Map<Menu, Integer> orderRepository = new EnumMap<>(Menu.class);
        Arrays.stream(inputValue.split(DELIMITER_MENU_ORDER))
                        .forEach(menuString -> addMenuToOrderRepository(orderRepository, menuString));
        validateOnlyDrink(orderRepository);
        validateMaxMenuCount(orderRepository);
        return orderRepository;
    }

    private static void addMenuToOrderRepository(Map<Menu, Integer> orderRepository, String menuString) {
        OrderedMenu orderedMenu = OrderedMenu.createByString(menuString);
        validateDuplicateMenu(orderRepository, orderedMenu.menu());
        orderRepository.put(orderedMenu.menu(), orderedMenu.count());
    }

    private static void validateDuplicateMenu(Map<Menu, Integer> result, Menu menu) {
        if (result.containsKey(menu)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private static void validateOnlyDrink(Map<Menu, Integer> result) {
        int drinkMenuCount = (int) result.keySet()
                .stream()
                .filter(menu -> menu.isSameType(FoodType.DRINK))
                .count();
        if (drinkMenuCount == result.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private static void validateMaxMenuCount(Map<Menu, Integer> result) {
        Integer totalMenuCount = result.values()
                .stream()
                .reduce(0, Integer::sum);
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }
}
