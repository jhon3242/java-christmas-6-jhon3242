package christmas.message;

import java.text.DecimalFormat;

public class ViewMessage {
    public static final String MINUS = "-";
    public static final String NOTHING = "없음";

    public static final String INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_ORDERS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static final DecimalFormat OUTPUT_FORMAT_MONEY = new DecimalFormat("###,###원");
    public static final String OUTPUT_WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String OUTPUT_TITLE_ORDER_MENU = "<주문 메뉴>";
    public static final String OUTPUT_TITLE_PRE_DISCOUNT_TOTAL_PRICE = "<할인 전 총주문 금액>";
    public static final String OUTPUT_TITLE_GIFT_MENU = "<증정 메뉴>";
    public static final String OUTPUT_TITLE_DISCOUNT = "<혜택 내역>";
    public static final String OUTPUT_TITLE_TOTAL_DISCOUNT = "<총혜택 금액>";
    public static final String OUTPUT_TITLE_DISCOUNTED_TOTAL_PRICE = "<할인 후 예상 결제 금액>";
    public static final String OUTPUT_TITLE_EVENT_BADGE = "<12월 이벤트 배지>";
    public static final String OUTPUT_FORMAT_EVENT_PRE_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    public static final String OUTPUT_FORMAT_DISCOUNT = "%s: -%s\n";
    public static final String OUTPUT_FORMAT_ORDER = "%s %d개\n";

    private ViewMessage() {
        // 불필요한 인스턴스화 방지
    }
}
