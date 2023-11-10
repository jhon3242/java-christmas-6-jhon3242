package christmas.view;

public class OutputView {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printException(IllegalArgumentException exception) {
        System.out.println("[ERROR] : " + exception.getMessage());
    }
}
