package calculator.model;

import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    private static final String ALLOWED_FROM = "^[0-9]*([:,][0-9]*)*$";
    private static final String ZERO_FROM = "";
    private static final String SEPARATOR = "\\D";
    private static final long ZERO = 0L;

    private static final String NUMBER_FROM = "^[0-9]+$";

    public StringCalculator() {
    }

    public void validateInputStringFrom(String inputString) {
        if (inputString.matches(ALLOWED_FROM) || inputString.matches(ZERO_FROM)) {
            return;
        }
        throw new IllegalArgumentException("문자열 입력 양식이 맞지 않습니다.");
    }

    public String getAllowedFrom() {
        return ALLOWED_FROM;
    }
    public String getZeroFrom() {
        return ALLOWED_FROM;
    }

    public void calculate(String inputString) {
        validateInputStringFrom(inputString);
    }

    public List<Long> filter(String inputString) {
        if(inputString.matches(ZERO_FROM)){
            return Arrays.asList(ZERO);
        }

        return Arrays.stream(inputString.split(SEPARATOR))
                .filter(token -> token.matches(NUMBER_FROM))
                .map(Long::parseLong)
                .toList();
    }
    public long add(List<Long> numbers){
            return numbers.stream()
                    .mapToLong(i -> i)
                    .sum();
    }
}
