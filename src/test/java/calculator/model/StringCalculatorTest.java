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
        String input1 = "";
        String input2 = "1:2";
        String input3 = "1:2,3";
        String input4 = "1,2,3";
        String input5 = "1:2:3";
        String input6 = "12:3456:789";

        // when
        String zeroFrom = stringCalculator.getZeroFrom();
        String allowedFrom = stringCalculator.getAllowedFrom();

        // then
        assertThat(input1.matches(allowedFrom)).isTrue();

        assertThat(input2.matches(allowedFrom)).isTrue();
        assertThat(input3.matches(allowedFrom)).isTrue();
        assertThat(input4.matches(allowedFrom)).isTrue();
        assertThat(input5.matches(allowedFrom)).isTrue();
        assertThat(input6.matches(allowedFrom)).isTrue();
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
        String input1 = "";
        String input2 = "1,2";
        String input3 = "1,2:3";
        String input4 = "13:27:3";
        String input5 = "139,20:3576";

        // when
        List<Long> numbers1 = stringCalculator.filter(input1);
        List<Long> numbers2 = stringCalculator.filter(input2);
        List<Long> numbers3 = stringCalculator.filter(input3);
        List<Long> numbers4 = stringCalculator.filter(input4);
        List<Long> numbers5 = stringCalculator.filter(input5);

        // then
        assertThat(numbers1).containsExactly(0L);
        assertThat(numbers2).containsExactly(1L, 2L);
        assertThat(numbers3).containsExactly(1L, 2L, 3L);
        assertThat(numbers4).containsExactly(13L, 27L, 3L);
        assertThat(numbers5).containsExactly(139L, 20L, 3576L);
    }

    @Test
    @DisplayName("덧셈 기능 테스트")
    public void addTest(){
        // given
        List<Long> numbers1 = Arrays.asList(100L, 376L, 44L);
        List<Long> numbers2 = Arrays.asList(0L);


        // when
        long result1 = stringCalculator.add(numbers1);
        long result2 = stringCalculator.add(numbers2);

        // then
        assertThat(result1).isEqualTo(520L);
        assertThat(result2).isEqualTo(0L);
    }
}
