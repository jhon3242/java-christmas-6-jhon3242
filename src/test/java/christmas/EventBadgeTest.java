package christmas;

import christmas.domain.EventBadge;
import christmas.domain.Money;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeTest {

    @DisplayName("총 할인 금액에 따라 이벤트 배지를 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateEventBadgeProvider")
    void calculateEventBadge(Money totalDiscountMoney, EventBadge expected) {
        EventBadge badge = EventBadge.findByTotalDiscountPrice(totalDiscountMoney);

        Assertions.assertThat(badge).isEqualTo(expected);
    }

    static Stream<Arguments> calculateEventBadgeProvider() {
        return Stream.of(
                Arguments.of(new Money(0), EventBadge.NONE),
                Arguments.of(new Money(4999), EventBadge.NONE),
                Arguments.of(new Money(5000), EventBadge.STAR),
                Arguments.of(new Money(9999), EventBadge.STAR),
                Arguments.of(new Money(10000), EventBadge.TREE),
                Arguments.of(new Money(19999), EventBadge.TREE),
                Arguments.of(new Money(20000), EventBadge.SANTA),
                Arguments.of(new Money(50000), EventBadge.SANTA)
        );
    }
}