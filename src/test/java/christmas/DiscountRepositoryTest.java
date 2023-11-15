package christmas;

import christmas.domain.DecemberDate;
import christmas.domain.Money;
import christmas.domain.discount.DiscountRepository;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountRepositoryTest {

    private static final OrderRepository GIFT_AVAILABLE_ORDER =
            OrderRepository.createByString("타파스-1,제로콜라-2,티본스테이크-4,바비큐립-3,초코케이크-5");
    private static final OrderRepository GIFT_UNAVAILABLE_ORDER =
            OrderRepository.createByString("타파스-1,제로콜라-1");
    private static final DecemberDate D_DAY_WEEKEND = new DecemberDate(1);
    private static final DecemberDate D_DAY_WEEKDAY = new DecemberDate(4);


    @DisplayName("증정 이벤트를 받는 경우 샴페인을 반환한다.")
    @Test
    void giftSuccessTest() {
        DiscountRepository repository = DiscountRepository.of(D_DAY_WEEKEND, GIFT_AVAILABLE_ORDER);

        Menu actual = repository.calculateGift();

        Assertions.assertThat(actual).isEqualTo(Menu.CHAMPAGNE);
    }

    @DisplayName("증정 이벤트를 못받는 경우 null을 반환한다.")
    @Test
    void giftFailTest() {
        DecemberDate dDayWeekend = new DecemberDate(1);
        DiscountRepository repository = DiscountRepository.of(D_DAY_WEEKEND, GIFT_UNAVAILABLE_ORDER);

        Menu actual = repository.calculateGift();

        Assertions.assertThat(actual).isNull();
    }

    @DisplayName("할인 금액을 계산한다.")
    @ParameterizedTest()
    @MethodSource("calculateTotalDiscountMoneyProvider")
    void calculateTotalDiscountMoney(DiscountRepository repository, Money expected) {
        Money actual = repository.calculateTotalDiscountMoney();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> calculateTotalDiscountMoneyProvider() {
        return Stream.of(
                Arguments.of(DiscountRepository.of(D_DAY_WEEKEND, GIFT_AVAILABLE_ORDER), new Money(40161)),
                Arguments.of(DiscountRepository.of(D_DAY_WEEKDAY, GIFT_AVAILABLE_ORDER), new Money(36415)),
                Arguments.of(DiscountRepository.of(D_DAY_WEEKEND, GIFT_UNAVAILABLE_ORDER), new Money(0))
        );
    }
}
