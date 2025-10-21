package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputCarName = Console.readLine();

        List<String> carList = Arrays.stream(inputCarName.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        for (String carName : carList) {
            if (carName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
            }
        }

        System.out.println("시도할 횟수는 몇 회인가요?");
        int inputTryCount = Integer.parseInt(Console.readLine());
    }
}
