package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public static void run() {
        OutputView.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        DecemberDate reserveDate = initReservationDate();
        Menus menus = initMenus();
        Reservation reservation = new Reservation(reserveDate, menus);
        OutputView.printMessage("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        OutputView.printMenus(menus);
        OutputView.printBeforeDiscountPrice(reservation.calculateTotalMoney());


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
