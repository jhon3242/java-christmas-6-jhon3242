package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.DDayDiscount;
import christmas.domain.discount.GiftDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekDiscount;
import christmas.domain.DecemberDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;
import christmas.domain.Money;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountTest {
    private static final DecemberDate WEEKEND = new DecemberDate(1);
    private static final DecemberDate WEEKDAY = new DecemberDate(3);

    private static OrderRepository makeMenusByList(List<Menu> menus) {
        Map<Menu, Integer> menuRepository = new HashMap<>();
        menus.forEach(menu ->
                menuRepository.put(menu, menuRepository.getOrDefault(menu, 0) + 1)
        );
        return new OrderRepository(menuRepository);
    }

    @DisplayName("크리스마스 디데이 이벤트 할인 금액 계산이 문제 없다.")
    @ParameterizedTest
    @MethodSource("dDayDiscountProvider")
    void dDayDiscount(DecemberDate date, Money expected) {
        Money actual = DDayDiscount.calculateDiscountAmount(date);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> dDayDiscountProvider() {
        return Stream.of(
                Arguments.of(new DecemberDate(1), new Money(1000)),
                Arguments.of(new DecemberDate(2), new Money(1100)),
                Arguments.of(new DecemberDate(3), new Money(1200)),
                Arguments.of(new DecemberDate(4), new Money(1300)),
                Arguments.of(new DecemberDate(10), new Money(1900)),
                Arguments.of(new DecemberDate(20), new Money(2900)),
                Arguments.of(new DecemberDate(25), new Money(3400)),
                Arguments.of(new DecemberDate(26), new Money(0)),
                Arguments.of(new DecemberDate(27), new Money(0)),
                Arguments.of(new DecemberDate(28), new Money(0)),
                Arguments.of(new DecemberDate(29), new Money(0)),
                Arguments.of(new DecemberDate(30), new Money(0)),
                Arguments.of(new DecemberDate(31), new Money(0))
        );
    }

    @DisplayName("주말에만 메인 메뉴 하나 당 2023원 할인이 적용된다.")
    @ParameterizedTest
    @MethodSource("weekendDiscountProvider")
    void weekendDiscount(OrderRepository menus, DecemberDate date, Money expected) {
        Money actual = WeekDiscount.calculateDiscountAmount(date, menus);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> weekendDiscountProvider() {
        return Stream.of(
                Arguments.of(makeMenusByList(List.of(Menu.CHAMPAGNE, Menu.BBQ_RIBS, Menu.CHOCO_CAKE)), WEEKEND,
                        new Money(2023)),
                Arguments.of(makeMenusByList(List.of(Menu.T_BORN_STAKE, Menu.BBQ_RIBS, Menu.SEA_FOOD_PASTA, Menu.CHRISTMAS_PASTA)), WEEKEND,
                        new Money(2023 * 4)),
                Arguments.of(makeMenusByList(List.of(Menu.CHAMPAGNE, Menu.BBQ_RIBS, Menu.T_BORN_STAKE)), WEEKDAY,
                        new Money(0))

        );
    }

    @DisplayName("평일에만 디저트 메뉴 하나 당 2023원 할인이 적용된다.")
    @ParameterizedTest
    @MethodSource("weekdayDiscountProvider")
    void weekdayDiscount(OrderRepository menus, DecemberDate date, Money expected) {
        Money actual = WeekDiscount.calculateDiscountAmount(date, menus);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> weekdayDiscountProvider() {
        return Stream.of(
                Arguments.of(makeMenusByList(List.of(Menu.CHAMPAGNE, Menu.CHOCO_CAKE, Menu.ICE_CREAM)), WEEKEND,
                        new Money(0)),
                Arguments.of(makeMenusByList(List.of(Menu.CHAMPAGNE, Menu.CHOCO_CAKE, Menu.T_BORN_STAKE)), WEEKDAY,
                        new Money(2023)
                ),
                Arguments.of(makeMenusByList(List.of(Menu.ICE_CREAM, Menu.CHOCO_CAKE)), WEEKDAY,
                        new Money(2023 * 2)
                )
        );
    }

    @DisplayName("이벤트 달력에 별이 있는 날에는 1000원 할인을 해준다.")
    @ParameterizedTest
    @MethodSource("specialDiscountProvider")
    void specialDiscount(DecemberDate date, Money expected) {
        Money actual = SpecialDiscount.calculateDiscountAmount(date);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> specialDiscountProvider() {
        return Stream.of(
                Arguments.of(new DecemberDate(3), new Money(1000)),
                Arguments.of(new DecemberDate(10), new Money(1000)),
                Arguments.of(new DecemberDate(17), new Money(1000)),
                Arguments.of(new DecemberDate(24), new Money(1000)),
                Arguments.of(new DecemberDate(25), new Money(1000)),
                Arguments.of(new DecemberDate(31), new Money(1000)),
                Arguments.of(new DecemberDate(1), new Money(0)),
                Arguments.of(new DecemberDate(2), new Money(0)),
                Arguments.of(new DecemberDate(29), new Money(0)),
                Arguments.of(new DecemberDate(30), new Money(0))
        );
    }

    @DisplayName("증정 할인 계산에 문제가 없다.")
    @ParameterizedTest
    @MethodSource("giftDiscountProvider")
    void giftDiscount(Money totalPrize, Money expected) {
        Money actual = GiftDiscount.calculateDiscountAmount(totalPrize);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> giftDiscountProvider() {
        return Stream.of(
                Arguments.of(new Money(10000), new Money(0)),
                Arguments.of(new Money(20000), new Money(0)),
                Arguments.of(new Money(50000), new Money(0)),
                Arguments.of(new Money(100000), new Money(0)),
                Arguments.of(new Money(119999), new Money(0)),
                Arguments.of(new Money(120000), new Money(25000)),
                Arguments.of(new Money(200000), new Money(25000))
        );
    }
}
