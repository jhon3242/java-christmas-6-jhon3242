package christmas.view;

import christmas.Menus;
import java.text.DecimalFormat;

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
                        .forEach((menu, count) -> System.out.printf("%s - %d개\n", menu.getName(), count));
        System.out.println();
    }

    public static void printBeforeDiscountPrice(int price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(MONEY_FORMAT.format(price));
    }
}
