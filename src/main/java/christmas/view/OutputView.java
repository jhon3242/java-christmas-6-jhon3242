package christmas.view;

import christmas.Menu;
import christmas.Menus;
import christmas.Money;
import java.text.DecimalFormat;
import java.util.Objects;

public class OutputView {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###,###원");

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printException(IllegalArgumentException exception) {
        System.out.println("[ERROR] : " + exception.getMessage());
    }

    public static void printMenus(Menus menus) {
        System.out.println("<주문 메뉴>");
        menus.menuRepository()
                        .forEach(OutputView::printMenu);
        System.out.println();
    }

    private static void printMenu(Menu menu, int count) {
        System.out.printf("%s - %d개\n", menu.getName(), count);
    }

    public static void printBeforeDiscountPrice(Money price) {
        System.out.println("<할인 전 총주문 금액>");
        printMoney(price);
        System.out.println();
    }

    private static void printMoney(Money money) {
        System.out.println(MONEY_FORMAT.format(money.amount()));
    }

    public static void printGift(Menu gift) {
        System.out.println("<증정 메뉴>");
        if (Objects.isNull(gift)) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        printMenu(gift, 1);
    }
}
