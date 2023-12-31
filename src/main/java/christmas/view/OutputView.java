package christmas.view;

import static christmas.message.ViewMessage.MINUS;
import static christmas.message.ViewMessage.NOTHING;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_EVENT_PRE_MESSAGE;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_ORDER;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_MONEY;
import static christmas.message.ViewMessage.OUTPUT_TITLE_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_TITLE_DISCOUNTED_TOTAL_PRICE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_EVENT_BADGE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_GIFT_MENU;
import static christmas.message.ViewMessage.OUTPUT_TITLE_ORDER_MENU;
import static christmas.message.ViewMessage.OUTPUT_TITLE_PRE_DISCOUNT_TOTAL_PRICE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_TOTAL_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_WELCOME_MESSAGE;

import christmas.domain.DecemberDate;
import christmas.domain.EventBadge;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;
import christmas.domain.Money;
import christmas.domain.discount.DiscountRepository;
import christmas.message.ExceptionMessage;
import java.util.Objects;

public class OutputView {
    public static void printWelcomeMessage() {
        System.out.println(OUTPUT_WELCOME_MESSAGE);
    }

    public static void printEventPreMessage(DecemberDate date) {
        System.out.printf(OUTPUT_FORMAT_EVENT_PRE_MESSAGE, date.dateAmount());
        System.out.println();
    }

    public static void printException(IllegalArgumentException exception) {
        System.out.println(ExceptionMessage.ERROR_PREFIX + exception.getMessage());
    }

    public static void printOrderMenus(OrderRepository orderRepository) {
        System.out.println(OUTPUT_TITLE_ORDER_MENU);
        orderRepository.repository()
                        .forEach(OutputView::printOrder);
        System.out.println();
    }

    private static void printOrder(Menu menu, int count) {
        System.out.printf(OUTPUT_FORMAT_ORDER, menu.getName(), count);
    }

    public static void printOriginalPrice(Money price) {
        System.out.println(OUTPUT_TITLE_PRE_DISCOUNT_TOTAL_PRICE);
        printMoney(price, false);
        System.out.println();
    }

    private static void printMoney(Money money, boolean isMinus) {
        boolean isNotZero = !money.isZero();
        if (isNotZero && isMinus) {
            System.out.print(MINUS);
        }
        System.out.println(OUTPUT_FORMAT_MONEY.format(money.amount()));
    }

    public static void printGiftMenu(Menu gift) {
        System.out.println(OUTPUT_TITLE_GIFT_MENU);
        if (Objects.isNull(gift)) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        printOrder(gift, 1);
        System.out.println();
    }

    public static void printDiscountLogs(DiscountRepository discountRepository) {
        System.out.println(OUTPUT_TITLE_DISCOUNT);
        if (discountRepository.isNoDiscount()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        discountRepository.getDiscountRepository()
                .forEach(OutputView::printDiscountLog);
        System.out.println();
    }

    private static void printDiscountLog(String discountName, Money discount) {
        if (discount.isZero()) {
            return;
        }
        System.out.printf(OUTPUT_FORMAT_DISCOUNT, discountName, OUTPUT_FORMAT_MONEY.format(discount.amount()));
    }

    public static void printTotalDiscountMoney(Money totalDiscountMoney) {
        System.out.println(OUTPUT_TITLE_TOTAL_DISCOUNT);
        printMoney(totalDiscountMoney, true);
        System.out.println();
    }

    public static void printToTalFinalPrice(Money finalPrice) {
        System.out.println(OUTPUT_TITLE_DISCOUNTED_TOTAL_PRICE);
        printMoney(finalPrice, false);
        System.out.println();
    }

    public static void printEventBadge(EventBadge eventBadge) {
        System.out.println(OUTPUT_TITLE_EVENT_BADGE);
        System.out.println(eventBadge.getName());
        System.out.println();
    }
}
