package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationTest {
    @DisplayName("크리스마스 디데이 + 평일 + 특별 할인 금액 계산이 문제 없다.")
    @Test
    void dDayAndWeekdayAndSpecialDiscount() {
        Map<Menu, Integer> menuIntegerMap = new HashMap<>();
        Menus menus = new Menus(menuIntegerMap);
        DecemberDate date = new DecemberDate(3);
        Reservation reservation = new Reservation(menus, date);

        menuIntegerMap.put(Menu.MUSHROOM_CREAM_SOUP, 2);
        menuIntegerMap.put(Menu.SEA_FOOD_PASTA, 5);
        menuIntegerMap.put(Menu.ICE_CREAM, 2);
        menuIntegerMap.put(Menu.CHAMPAGNE, 1);
        Money expected = new Money(1200 + 2023 * 2 + 1000);

        assertThat(reservation.calculateTotalDiscountMoney()).isEqualTo(expected);
    }

    @DisplayName("크리스마스 디데이 + 주말 + 증정 할인 금액 계산이 문제 없다.")
    @Test
    void dDayAndWeekendAndSpecialAndGiftDiscount() {
        Map<Menu, Integer> menuIntegerMap = new HashMap<>();
        Menus menus = new Menus(menuIntegerMap);
        DecemberDate date = new DecemberDate(9);
        Reservation reservation = new Reservation(menus, date);

        menuIntegerMap.put(Menu.MUSHROOM_CREAM_SOUP, 2);
        menuIntegerMap.put(Menu.SEA_FOOD_PASTA, 5);
        menuIntegerMap.put(Menu.RED_WINE, 1);
        menuIntegerMap.put(Menu.ICE_CREAM, 2);
        menuIntegerMap.put(Menu.CHAMPAGNE, 1);
        Money expected = new Money(1800 + 2023 * 5 + 25000);

        assertThat(reservation.calculateTotalDiscountMoney()).isEqualTo(expected);
    }

//    @ParameterizedTest()
//    @MethodSource("totalDiscountAmountProvider")
//    void totalDiscountAmount(Reservation reservation, Money expected) {
//        Money totalDiscountAmount = reservation.calculateTotalDiscountAmount();
//
//        assertThat(totalDiscountAmount).isEqualTo(expected);
//    }
//
//    static Stream<Arguments> totalDiscountAmountProvider() {
//        return Stream.of(
//        )
//    }
}
