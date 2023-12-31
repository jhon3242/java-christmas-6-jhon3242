package christmas.domain.menu;

import christmas.message.ExceptionMessage;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Order(Menu menu, int count) {
    private static final String FORMAT_MENU_ORDER = "([가-힣]+)-(\\d+)";
    private static final Pattern PATTERN_MENU = Pattern.compile(FORMAT_MENU_ORDER);
    private static final int NAME_INDEX = 1;
    private static final int COUNT_INDEX = 2;

    public Order {
        validateNotNull(menu);
        validateCount(count);
    }

    public static Order createByString(String menuString) {
        Matcher matcher = PATTERN_MENU.matcher(menuString);
        validateFormat(matcher);
        Menu menu = Menu.findByName(matcher.group(NAME_INDEX));
        int count = Integer.parseInt(matcher.group(COUNT_INDEX));
        return new Order(menu, count);
    }

    private static void validateFormat(Matcher matcher) {
        if (matcher.matches()) {
            return;
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
    }

    private static void validateNotNull(Menu menu) {
        if (Objects.isNull(menu)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private static void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER);
        }
    }
}
