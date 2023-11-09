package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.discount.DDayDiscount;
import christmas.discount.WeekDiscount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountTest {

    @DisplayName("크리스마스 디데이 이벤트 할인 금액 계산이 문제 없다.")
    @ParameterizedTest
    @MethodSource("dDayDiscountProvider")
    void dDayDiscount(DecemberDate date, Money expected) {
        // given
        DDayDiscount discount = new DDayDiscount();

        // when
        Money actual = discount.calculateDiscountAmount(date);

        // then
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

    @DisplayName("주말에만 메 하나 당 2023원 할인이 적용된다.")
    @ParameterizedTest
    @MethodSource("weekDiscountProvider")
    void weekendDiscount(DecemberDate date, Money expected) {
        // given
        WeekDiscount discount = new WeekDiscount();
        Map<Menu, Integer> menuRepository = new HashMap<>();
        menuRepository.put(Menu.T_BORN_STAKE, 1);
        menuRepository.put(Menu.BBQ_RIBS, 1);
        menuRepository.put(Menu.MUSHROOM_CREAM_SOUP, 1);
        // when
        Menus menus = new Menus(menuRepository);
        Money actual = discount.calculateDiscountAmount(date, menus);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> weekDiscountProvider() {
        return Stream.of(
                Arguments.of(new DecemberDate(1), new Money(2023 * 2)),
                Arguments.of(new DecemberDate(2), new Money(2023 * 2)),
                Arguments.of(new DecemberDate(3), new Money(0)),
                Arguments.of(new DecemberDate(4), new Money(0)));
    }
}
