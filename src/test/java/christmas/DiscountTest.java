package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountTest {

    @DisplayName("크리스마스 디데이 이벤트 기간 중 예약한 경우 할인을 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("dDayDiscountProvider")
    void dDayDiscount(Date date, Money expected) {
        // given
        Discount discount = new Discount();

        // when
        Money actual = discount.dDayDiscount(date);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> dDayDiscountProvider() {
        return Stream.of(
                Arguments.of(new Date(1), new Money(1000)),
                Arguments.of(new Date(2), new Money(1100)),
                Arguments.of(new Date(3), new Money(1200)),
                Arguments.of(new Date(4), new Money(1300)),
                Arguments.of(new Date(10), new Money(1900)),
                Arguments.of(new Date(20), new Money(2900)),
                Arguments.of(new Date(25), new Money(3400)),
                Arguments.of(new Date(26), new Money(0)),
                Arguments.of(new Date(27), new Money(0)),
                Arguments.of(new Date(28), new Money(0)),
                Arguments.of(new Date(29), new Money(0)),
                Arguments.of(new Date(30), new Money(0)),
                Arguments.of(new Date(31), new Money(0))
        )
    }
}
