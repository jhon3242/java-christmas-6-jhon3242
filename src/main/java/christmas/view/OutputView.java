package christmas.view;

import static christmas.message.ViewMessage.MINUS;
import static christmas.message.ViewMessage.NOTHING;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_EVENT_PRE_MESSAGE;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_MENU;
import static christmas.message.ViewMessage.OUTPUT_FORMAT_MONEY;
import static christmas.message.ViewMessage.OUTPUT_TITLE_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_TITLE_DISCOUNTED_TOTAL_PRICE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_EVENT_BADGE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_GIFT_MENU;
import static christmas.message.ViewMessage.OUTPUT_TITLE_ORDER_MENU;
import static christmas.message.ViewMessage.OUTPUT_TITLE_PRE_DISCOUNT_TOTAL_PRICE;
import static christmas.message.ViewMessage.OUTPUT_TITLE_TOTAL_DISCOUNT;
import static christmas.message.ViewMessage.OUTPUT_WELCOME_MESSAGE;

import christmas.DecemberDate;
import christmas.EventBadge;
import christmas.Menu;
import christmas.Menus;
import christmas.Money;
import christmas.discount.DiscountRepository;
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

    public static void printOrderMenus(Menus menus) {
        System.out.println(OUTPUT_TITLE_ORDER_MENU);
        menus.menuRepository()
                        .forEach(OutputView::printMenu);
        System.out.println();
    }

    private static void printMenu(Menu menu, int count) {
        System.out.printf(OUTPUT_FORMAT_MENU, menu.getName(), count);
    }

    public static void printOriginalPrice(Money price) {
        System.out.println(OUTPUT_TITLE_PRE_DISCOUNT_TOTAL_PRICE);
        printMoney(price, false);
        System.out.println();
    }

    private static void printMoney(Money money, boolean isMinus) {
        boolean isNotZero = !Objects.equals(money.amount(), 0);
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
        printMenu(gift, 1);
        System.out.println();
    }

    public static void printDiscountLogs(DiscountRepository discountRepository) {
        System.out.println(OUTPUT_TITLE_DISCOUNT);
        if (discountRepository.isNotDiscount()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        discountRepository.getDiscountRepository()
                .forEach(OutputView::printDiscountLog);
        System.out.println();
    }

    private static void printDiscountLog(String key, Money discount) {
        if (Objects.equals(discount, new Money(0))) {
            return;
        }
        System.out.printf(OUTPUT_FORMAT_DISCOUNT, key, OUTPUT_FORMAT_MONEY.format(discount.amount()));
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
