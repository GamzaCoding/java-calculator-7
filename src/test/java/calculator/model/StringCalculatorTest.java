package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("허용된 문자열 양식 검증 테스트")
    public void InputStringFromTest() {
        // given
        String input1 = "1:2,3";
        String input2 = "1,2,3";
        String input3 = "1:2:3";
        String input4 = "12:3456:789";

        // when
        String allowedFrom = stringCalculator.getAllowedFrom();

        // then
        assertThat(input1.matches(allowedFrom)).isTrue();
        assertThat(input2.matches(allowedFrom)).isTrue();
        assertThat(input3.matches(allowedFrom)).isTrue();
        assertThat(input4.matches(allowedFrom)).isTrue();
    }

    @Test
    @DisplayName("잘못된 양식의 문자열 검증 테스트")
    public void notAllowedFromTest() {
        // given
        String input = "1.2:3";

        // when, then
        assertThatThrownBy(() -> stringCalculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 필터링 기능 테스트")
    public void filterNumberTest() {
        // given
        String input1 = "1,2:3";
        String input2 = "13:27:3";
        String input3 = "139,20:3576";

        // when
        List<Long> numbers1 = stringCalculator.filter(input1);
        List<Long> numbers2 = stringCalculator.filter(input2);
        List<Long> numbers3 = stringCalculator.filter(input3);

        // then
        assertThat(numbers1).containsExactly(1L, 2L, 3L);
        assertThat(numbers2).containsExactly(13L, 27L, 3L);
        assertThat(numbers3).containsExactly(139L, 20L, 3576L);
    }

    @Test
    @DisplayName("덧셈 기능 테스트")
    public void addTest(){
        // given
        List<Long> numbers = Arrays.asList(100L, 376L, 44L);

        // when
        long result = stringCalculator.add(numbers);

        // then
        assertThat(result).isEqualTo(520L);
    }
}
