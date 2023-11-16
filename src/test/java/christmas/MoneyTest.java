package christmas;

import christmas.domain.Money;
import christmas.message.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @DisplayName("금액은 0원 이상인 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 100, 1000, 999999, 1000000})
    void moneySuccessTest(int amount) {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Money(amount));
    }

    @DisplayName("금액은 0원 미만인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -100, -1000, -999999, -1000000})
    void moneyFailTest(int amount) {
        Assertions.assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_MONEY);
    }
}
