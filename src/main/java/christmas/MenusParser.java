package christmas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenusParser {
    private static final Pattern pattern = Pattern.compile("([가-힣]+)-(\\d+)");

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
        return result;
    }

    private static void validateFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateMenu(Map<Menu, Integer> result, Menu menu) {
        if (Objects.isNull(menu)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (hasSameMenu(result, menu)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean hasSameMenu(Map<Menu, Integer> result, Menu menu) {
        return result.containsKey(menu);
    }

    private static void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
