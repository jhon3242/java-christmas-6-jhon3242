package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.discount.DDayDiscount;
import christmas.domain.discount.GiftDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekDiscount;
import christmas.domain.DecemberDate;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final DecemberDate WEEKEND = new DecemberDate(1);
    private static final DecemberDate WEEKDAY = new DecemberDate(4);

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 오직_디데이_할인() {
        assertSimpleTest(() -> {
            run("4", "티본스테이크-1");

            assertThat(output())
                    .doesNotContain(
                        SpecialDiscount.NAME,
                        WeekDiscount.getNameByDate(WEEKEND),
                        WeekDiscount.getNameByDate(WEEKDAY),
                        GiftDiscount.NAME
                    ).contains(DDayDiscount.NAME);
        });
    }

    @Test
    void 오직_주말_할인() {
        assertSimpleTest(() -> {
            run("29", "티본스테이크-1");

            assertThat(output())
                    .doesNotContain(
                        DDayDiscount.NAME,
                        WeekDiscount.getNameByDate(WEEKDAY),
                        SpecialDiscount.NAME,
                        GiftDiscount.NAME
                    ).contains(WeekDiscount.getNameByDate(WEEKEND));
        });
    }

    @Test
    void 오직_평일_할인() {
        assertSimpleTest(() -> {
            run("26", "아이스크림-2");

            assertThat(output())
                    .doesNotContain(
                        DDayDiscount.NAME,
                        WeekDiscount.getNameByDate(WEEKEND),
                        SpecialDiscount.NAME,
                        GiftDiscount.NAME
                    ).contains(WeekDiscount.getNameByDate(WEEKDAY));
        });
    }

    @Test
    void 오직_특별_할인() {
        assertSimpleTest(() -> {
            run("31", "티본스테이크-1");

            assertThat(output())
                    .doesNotContain(
                        DDayDiscount.NAME,
                        WeekDiscount.getNameByDate(WEEKDAY),
                        WeekDiscount.getNameByDate(WEEKEND),
                        GiftDiscount.NAME
                    ).contains(SpecialDiscount.NAME);
        });
    }

    @Test
    void 오직_증정_할인() {
        assertSimpleTest(() -> {
            run("28", "티본스테이크-3");

            assertThat(output())
                    .doesNotContain(
                        DDayDiscount.NAME,
                        WeekDiscount.getNameByDate(WEEKDAY),
                        WeekDiscount.getNameByDate(WEEKEND),
                        SpecialDiscount.NAME
                    ).contains(GiftDiscount.NAME);
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
