package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class ReservationController {
    public static void run() {
        OutputView.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        DecemberDate reserveDate = initReservationDate();
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

//    private static <T> T inputValue(Supplier<T> supplier) {
//        try {
//            return supplier.get();
//        } catch (IllegalArgumentException exception) {
//            OutputView.printException(exception);
//            return inputValue(supplier);
//        }
//    }
}
