package christmas;

import camp.nextstep.edu.missionutils.Randoms;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DecemberDateTest {

    @DisplayName("12월 1일부터 31일 사이의 날짜가 정상적으로 생성된다.")
    @RepeatedTest(50)
    void decemberDateValidTest() {
        int pickedAmount = Randoms.pickNumberInRange(1, 31);

        Assertions.assertThatNoException()
                .isThrownBy(() -> new DecemberDate(pickedAmount));
    }

    @DisplayName("12월 1일부터 31일 사이의 날짜가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -5, 0, 32, 33, 34, 35, 36, 37, 38, 39, 40})
    void decemberDateInvalidTest(int dateAmount) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new DecemberDate(dateAmount));
    }
}
