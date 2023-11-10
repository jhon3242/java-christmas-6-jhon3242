package christmas.view;

import christmas.Menus;

public class OutputView {
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
}
