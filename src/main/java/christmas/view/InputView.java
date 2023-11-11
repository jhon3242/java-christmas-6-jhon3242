package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.message.ExceptionMessage;
import christmas.message.ViewMessage;
import java.util.Objects;

public class InputView {
    public static int readDate() {
        System.out.println(ViewMessage.INPUT_DATE);
        return readInteger();
    }

    public static String readMenu() {
        System.out.println(ViewMessage.INPUT_MENU);
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
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE);
        }
    }

    public static void validateBlank(String inputValue) {
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE);
        }
    }

    // TODO 정규표현식으로 리팩터링
    private static void validateInteger(String inputValue) {
        try {
            Integer.parseInt(inputValue);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE);
        }
    }
}
