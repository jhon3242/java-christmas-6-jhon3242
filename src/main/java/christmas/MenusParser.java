package christmas;

import christmas.message.ExceptionMessage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenusParser {
    private static final String FORMAT_MENU_ORDER = "([가-힣]+)-(\\d+)";
    private static final Pattern pattern = Pattern.compile(FORMAT_MENU_ORDER);
    private static final int MAX_MENU_COUNT = 20;
    private static final String DELIMITER_MENU_ORDER = ",";
    public static final int NAME_INDEX = 1;
    public static final int COUNT_INDEX = 2;

    public static Map<Menu, Integer> parse(String inputValue) {
        Map<Menu, Integer> orderRepository = new EnumMap<>(Menu.class);
        Arrays.stream(inputValue.split(DELIMITER_MENU_ORDER))
                        .forEach(menuString -> addMenuToOrderRepository(orderRepository, menuString));
        validateOnlyDrink(orderRepository);
        validateMaxMenuCount(orderRepository);
        return orderRepository;
    }

    private static void addMenuToOrderRepository(Map<Menu, Integer> orderRepository, String menuString) {
        Matcher matcher = pattern.matcher(menuString);
        validateFormat(matcher);
        Menu menu = Menu.findByName(matcher.group(NAME_INDEX));
        int count = Integer.parseInt(matcher.group(COUNT_INDEX));
        validateMenu(orderRepository, menu);
        validateCount(count);
        orderRepository.put(menu, count);
    }

    private static void validateFormat(Matcher matcher) {
        if (matcher.matches()) {
            return;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
    }

    private static void validateMenu(Map<Menu, Integer> result, Menu menu) {
        if (Objects.isNull(menu)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
        if (hasSameMenu(result, menu)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private static boolean hasSameMenu(Map<Menu, Integer> result, Menu menu) {
        return result.containsKey(menu);
    }

    private static void validateCount(int count) {
        if (count <= 0) {
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
