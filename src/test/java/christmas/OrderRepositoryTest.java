package christmas;

import christmas.domain.Money;
import christmas.domain.menu.FoodType;
import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderRepository;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderRepositoryTest {
    private static OrderRepository orderRepository;

    @BeforeAll
    static void setUp() {
        orderRepository = OrderRepository.createByString("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
    }

    @DisplayName("메뉴 타입에 따른 음식 개수를 계산한다.")
    @ParameterizedTest
    @MethodSource("findTotalCountByFoodTypeProvider")
    void findTotalCountByFoodType(FoodType type, int expected) {
        int actual = orderRepository.findTotalCountByFoodType(type);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> findTotalCountByFoodTypeProvider() {
        return Stream.of(
                Arguments.of(FoodType.APPETIZER, 0),
                Arguments.of(FoodType.MAIN_COURSE, 2),
                Arguments.of(FoodType.DESSERT, 2),
                Arguments.of(FoodType.DRINK, 1)
        );
    }

    @DisplayName("총 메뉴 가격을 계산한다.")
    @Test
    void calculateTotalPrice() {
        Money actual = new Money(0);

        actual = Money.sum(actual, Menu.T_BORN_STAKE.getPrice());
        actual = Money.sum(actual, Menu.BBQ_RIBS.getPrice());
        actual = Money.sum(actual, Menu.CHOCO_CAKE.getPrice().multiply(2));
        actual = Money.sum(actual, Menu.ZERO_COKE.getPrice());

        Assertions.assertThat(actual).isEqualTo(new Money(142000));
    }

}
