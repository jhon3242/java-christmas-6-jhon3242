package christmas;

import christmas.domain.DecemberDate;
import christmas.domain.menu.Menus;
import christmas.domain.Money;
import christmas.domain.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TotalFinalPriceTest {


    @DisplayName("크리스마스 디데이 + 평일 + 특별 할인 후 최종 계산 금액에 문제가 없다.")
    @Test
    void totalDiscountedWithoutGiftPrice() {
        Menus allTypeMenus = Menus.createByString("양송이수프-1,티본스테이크-1,초코케이크-1,제로콜라-1");
        DecemberDate dDayWeekdayDate = new DecemberDate(4);
        Reservation reservation = Reservation.of(dDayWeekdayDate, allTypeMenus);

        Money totalDiscountedMoney = reservation.calculateTotalFinalMoney();
        Money expected = allTypeMenus.calculateTotalPrice()
                .minus(new Money(1300 + 2023));

        Assertions.assertThat(totalDiscountedMoney).isEqualTo(expected);
    }

    @DisplayName("크리스마스 디데이 + 평일 + 특별 + 증정 할인 후 최종 계산 금액에 문제가 없다.")
    @Test
    void totalDiscountedWithGiftPrice() {
        Menus allTypeMenus = Menus.createByString("양송이수프-3,티본스테이크-3,초코케이크-1,제로콜라-1");
        DecemberDate dDayWeekdayDate = new DecemberDate(4);
        Reservation reservation = Reservation.of(dDayWeekdayDate, allTypeMenus);

        Money totalDiscountedMoney = reservation.calculateTotalFinalMoney();
        Money expected = allTypeMenus.calculateTotalPrice()
                .minus(new Money(1300 + 2023));

        Assertions.assertThat(totalDiscountedMoney).isEqualTo(expected);
    }
}
