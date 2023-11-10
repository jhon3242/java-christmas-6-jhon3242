package christmas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenusTest {

    @DisplayName("메뉴의 형식이 올바른 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1", "타파스-1", "티본스테이크-1,바비큐립-1,초코케이크-10,제로콜라-8"})
    void menusValidFormat(String inputValue) {
        Assertions.assertThatNoException().isThrownBy(() -> {
            Menus.createByString(inputValue);
        });
    }

    @DisplayName("메뉴의 형식이 잘못된 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-a", "타파스-1 제로콜라-2"})
    void menusInvalidFormat(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴에 포함되지 않은 메뉴가 있는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"없는메뉴-1,제로콜라-1", "없는메뉴-1,없는메뉴-2"})
    void menusNotIncludeMenu(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴가 있는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,타파스-1", "제로콜라-1,제로콜라-2"})
    void menusDuplicateMenu(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 수량이 올바르지 않는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-0,제로콜라-1", "타파스-1,제로콜라-1.2", "타파스-1,제로콜라--10"})
    void menusInvalidCountMenu(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료 메뉴만 주문한 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "레드와인-1,제로콜라-1", "샴페인-1,레드와인-10"})
    void menusOnlyDrinkMenu(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최대 주문 개수를 초과하는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-11,제로콜라-10", "타파스-25", "티본스테이크-10,바비큐립-5,초코케이크-10,제로콜라-5"})
    void menusMaxMenuCount(String inputValue) {
        Assertions.assertThatThrownBy(() -> {
            Menus.createByString(inputValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
