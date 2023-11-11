package christmas.view;

import christmas.DecemberDate;
import christmas.Menu;
import christmas.Menus;
import christmas.Money;
import christmas.message.ExceptionMessage;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;

public class OutputView {
    private static final String OUTPUT_WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final DecimalFormat FORMAT_MONEY = new DecimalFormat("###,###원");
    public static final String FORMAT_EVENT_PRE_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String FORMAT_MENU = "%s - %d개\n";
    public static final String TITLE_ORDER_MENU = "<주문 메뉴>";
    public static final String TITLE_PRE_DISCOUNT_TOTAL_PRICE = "<할인 전 총주문 금액>";
    public static final String MINUS = "-";
    public static final String TITLE_GIFT_MENU = "<증정 메뉴>";
    public static final String NOTHING = "없음";
    public static final String FORMAT_DISCOUNT = "%s: -%s\n";
    public static final String TITLE_DISCOUNT = "<혜택 내역>";
    public static final String TITLE_TOTAL_DISCOUNT = "<총혜택 금액>";
    public static final String TITLE_DISCOUNTED_TOTAL_PRICE = "<할인 후 예상 결제 금액>";
    public static final String TITLE_EVENT_BADGE = "<12월 이벤트 배지>";

    public static void printWelcome() {
        System.out.println(OUTPUT_WELCOME_MESSAGE);
    }

    public static void printEventPreMessage(DecemberDate date) {
        System.out.printf(FORMAT_EVENT_PRE_MESSAGE, date.dateAmount());
    }

    public static void printException(IllegalArgumentException exception) {
        System.out.println(ExceptionMessage.ERROR_PREFIX + exception.getMessage());
    }

    public static void printOrderMenus(Menus menus) {
        System.out.println(TITLE_ORDER_MENU);
        menus.menuRepository()
                        .forEach(OutputView::printMenu);
        System.out.println();
    }

    private static void printMenu(Menu menu, int count) {
        System.out.printf(FORMAT_MENU, menu.getName(), count);
    }

    public static void printBeforeDiscountPrice(Money price) {
        System.out.println(TITLE_PRE_DISCOUNT_TOTAL_PRICE);
        printMoney(price, false);
        System.out.println();
    }

    private static void printMoney(Money money, boolean isMinus) {
        boolean isNotZero = !Objects.equals(money.amount(), 0);
        if (isNotZero && isMinus) {
            System.out.print(MINUS);
        }
        System.out.println(FORMAT_MONEY.format(money.amount()));
    }

    public static void printGift(Menu gift) {
        System.out.println(TITLE_GIFT_MENU);
        if (Objects.isNull(gift)) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        printMenu(gift, 1);
        System.out.println();
    }

    public static void printDiscountLogs(Map<String, Money> discountLog) {
        System.out.println(TITLE_DISCOUNT);
        if (isNoDiscount(discountLog)) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        discountLog.forEach(OutputView::printDiscountLog);
        System.out.println();
    }

    private static void printDiscountLog(String key, Money discount) {
        System.out.printf(FORMAT_DISCOUNT, key, FORMAT_MONEY.format(discount.amount()));
    }

    private static boolean isNoDiscount(Map<String, Money> discountLog) {
        return discountLog.size() == 0;
    }

    public static void printTotalDiscountMoney(Money totalDiscountMoney) {
        System.out.println(TITLE_TOTAL_DISCOUNT);
        printMoney(totalDiscountMoney, true);
        System.out.println();
    }

    public static void printFinalPrice(Money finalPrice) {
        System.out.println(TITLE_DISCOUNTED_TOTAL_PRICE);
        printMoney(finalPrice, false);
        System.out.println();
    }


    public static void printEventBadge(String eventBadge) {
        System.out.println(TITLE_EVENT_BADGE);
        System.out.println(eventBadge);
        System.out.println();
    }
}
