package christmas;

import christmas.domain.DecemberDate;
import christmas.domain.menu.OrderRepository;
import christmas.domain.Reservation;
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
        OrderRepository orderRepository = initOrderRepository();
        return Reservation.of(date, orderRepository);
    }

    private static DecemberDate initDecemberDate() {
        return receiveValidatedValue(() -> new DecemberDate(InputView.readDate()));
    }

    private static OrderRepository initOrderRepository() {
        return receiveValidatedValue(() -> OrderRepository.createByString(InputView.readOrders()));
    }

    private static <T> T receiveValidatedValue(Supplier<T> inputMethod) {
        try {
            return inputMethod.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return receiveValidatedValue(inputMethod);
        }
    }

    private static void printOrderInformation(Reservation reservation) {
        OutputView.printEventPreMessage(reservation.getDecemberDate());
        OutputView.printOrderMenus(reservation.getOrderRepository());
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
