package christmas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenusParser {
    private static final Pattern pattern = Pattern.compile("([가-힣]+)-(\\d+)");
    private static final String MENU_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final int MAX_MENU_COUNT = 20;

    public static Map<Menu, Integer> parse(String inputValue) {
        Map<Menu, Integer> result = new HashMap<>();
        Arrays.stream(inputValue.split(","))
                        .forEach(menuString -> {
                            Matcher matcher = pattern.matcher(menuString);
                            validateFormat(matcher);
                            Menu menu = Menu.findByName(matcher.group(1));
                            validateMenu(result, menu);
                            int count = Integer.parseInt(matcher.group(2));
                            validateCount(count);
                            result.put(menu, count);
                        });
        validateOnlyDrink(result);
        validateMaxMenuCount(result);
        return result;
    }

    private static void validateFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
    }

    private static void validateMenu(Map<Menu, Integer> result, Menu menu) {
        if (Objects.isNull(menu)) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
        if (hasSameMenu(result, menu)) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
    }

    private static boolean hasSameMenu(Map<Menu, Integer> result, Menu menu) {
        return result.containsKey(menu);
    }

    private static void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
    }

    private static void validateOnlyDrink(Map<Menu, Integer> result) {
        int drinkMenuCount = (int) result.keySet()
                .stream()
                .filter(menu -> menu.isSameType(FoodType.DRINK))
                .count();
        if (drinkMenuCount == result.size()) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
    }

    private static void validateMaxMenuCount(Map<Menu, Integer> result) {
        Integer totalMenuCount = result.values()
                .stream()
                .reduce(0, Integer::sum);
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(MENU_EXCEPTION_MESSAGE);
        }
    }
}
