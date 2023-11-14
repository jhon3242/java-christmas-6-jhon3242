package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class ChristmasController {
    public static void run() {
        Reservation reservation = initReservation();
        printOrderInformation(reservation);
        printDiscountInformation(reservation);
    }

    private static Reservation initReservation() {
        OutputView.printWelcomeMessage();
        DecemberDate date = initDecemberDate();
        Menus menus = initMenus();
        return Reservation.of(date, menus);
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

    private static void printOrderInformation(Reservation reservation) {
        OutputView.printEventPreMessage(reservation.getDecemberDate());
        OutputView.printOrderMenus(reservation.getMenus());
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
