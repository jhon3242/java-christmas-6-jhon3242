package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ReservationController {
    public static void run() {
        OutputView.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        DecemberDate reserveDate = new DecemberDate(InputView.readDate());
        OutputView.printMessage(reserveDate.toString());
    }
}
