package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {


    public static void run() {
        OutputView.printWelcome();

        DecemberDate reserveDate = initReservationDate();
        Menus menus = initMenus();
        Reservation reservation = new Reservation(reserveDate, menus);

        reservation.calculateDiscount();

        OutputView.printEventPreMessage(reserveDate);
        OutputView.printOrderMenus(menus);
        printDiscountInformation(reservation);
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

    private static void printDiscountInformation(Reservation reservation) {
        OutputView.printBeforeDiscountPrice(reservation.calculateTotalMoney());
        OutputView.printGift(reservation.calculateGift());
        OutputView.printDiscountLogs(reservation.getDiscountRepository());
        OutputView.printTotalDiscountMoney(reservation.calculateTotalDiscountMoney());
        OutputView.printFinalPrice(reservation.calculateTotalDiscountedMoney());
        OutputView.printEventBadge(reservation.calculateEventBadge());
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
