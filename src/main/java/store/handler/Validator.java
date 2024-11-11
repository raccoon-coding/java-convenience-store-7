package store.handler;

import store.exception.InvalidNumberFormatException;
import store.exception.IsNegativeNumber;
import store.exception.NotYesOrNo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Validator {
    private static final String DASH = "-";
    private static final String COMMA = ",";

    public Map<String, Integer> purchaseProducts(String input) {
        List<String> strings = splitFromListByComma(input);
        return splitFromMapByDash(strings);
    }

    public Boolean getUserAgreement(String input) {
        validYorN(input);
        return positiveAgreement(input);
    }

    private List<String> splitFromListByComma(String input) {
        List<String> result = List.of(input.split(COMMA));
        return result.stream()
                .map(s -> s.replaceAll("[\\[\\]]", ""))
                .toList();
    }

    private Map<String, Integer> splitFromMapByDash(List<String> input) {
        return input.stream()
                .map(string -> string.split(DASH))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> parseInt(arr[1])
                ));
    }

    private Integer parseInt(String number) {
        try{
            Integer num = Integer.parseInt(number);
            isPositiveNumber(num);
            return num;
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void isPositiveNumber(Integer num) {
        if(num <= 0) {
            throw new IsNegativeNumber("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void validYorN(String input) {
        if(!input.equals("Y") && !input.equals("N")) {
            throw new NotYesOrNo("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

    private Boolean positiveAgreement(String input) {
        return input.equals("Y");
    }
}
