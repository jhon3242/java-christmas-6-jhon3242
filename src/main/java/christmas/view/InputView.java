package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Objects;

public class InputView {
    private static final String INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String INVALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static int readDate() {
        System.out.println(INPUT_DATE);
        return readInteger();
    }

    public static String readMenu() {
        System.out.println(INPUT_MENU);
        return Console.readLine();
    }

    private static int readInteger() {
        String inputValue = Console.readLine().trim();
        validateNull(inputValue);
        validateBlank(inputValue);
        validateInteger(inputValue);
        return Integer.parseInt(inputValue);
    }

    public static void validateNull(String inputValue) {
        if (Objects.isNull(inputValue)) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    public static void validateBlank(String inputValue) {
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    // TODO 정규표현식으로 리팩터링
    private static void validateInteger(String inputValue) {
        try {
            Integer.parseInt(inputValue);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }
}
