package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public static void run() {
        OutputView.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        DecemberDate reserveDate = initReservationDate();
        Menus menus = initMenus();
        OutputView.printMessage(reserveDate.toString());
    }

    private static DecemberDate initReservationDate() {
        try {
            return new DecemberDate(InputView.readDate());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return initReservationDate();
        }
    }

    private static Menus initMenus() {
        try {
            return Menus.createByString(InputView.readMenu());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return initMenus();
        }
    }

//    private static <T> T inputValue(Supplier<T> supplier) {
//        try {
//            return supplier.get();
//        } catch (IllegalArgumentException exception) {
//            OutputView.printException(exception);
//            return inputValue(supplier);
//        }
//    }
}
