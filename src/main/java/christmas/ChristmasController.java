package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class ChristmasController {
    public static void run() {
        OutputView.printWelcome();

        DecemberDate reserveDate = initDecemberDate();
        Menus menus = initMenus();
        Reservation reservation = Reservation.of(reserveDate, menus);

        OutputView.printEventPreMessage(reserveDate);
        OutputView.printOrderMenus(menus);
        printDiscountInformation(reservation);
    }

    private static DecemberDate initDecemberDate() {
        return getValidValue(() -> new DecemberDate(InputView.readDate()));
    }

    private static Menus initMenus() {
        return getValidValue(() -> Menus.createByString(InputView.readMenu()));
    }

    private static <T> T getValidValue(Supplier<T> inputMethod) {
        try {
            return inputMethod.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return getValidValue(inputMethod);
        }
    }

    private static void printDiscountInformation(Reservation reservation) {
        OutputView.printOriginalPrice(reservation.calculateTotalOriginalMoney());
        OutputView.printGiftMenu(reservation.calculateGift());
        OutputView.printDiscountLogs(reservation.getDiscountRepository());
        OutputView.printTotalDiscountMoney(reservation.calculateTotalDiscountMoney());
        OutputView.printToTalFinalPrice(reservation.calculateTotalFinalMoney());
        OutputView.printEventBadge(reservation.calculateEventBadge());
    }
}
