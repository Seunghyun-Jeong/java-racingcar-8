package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputCarName = Console.readLine();
        List<Car> cars = inputCars(inputCarName);

        System.out.println("시도할 횟수는 몇 회인가요?");
        int tryCount = inputTryCount();

        System.out.println("실행 결과");
        playRacing(cars, tryCount);
        printWinners(cars);
    }

    private static List<Car> inputCars(String carName) {
        String[] names = carName.split(",");
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            name = name.trim();
            cars.add(new Car(name));
        }

        return cars;
    }

    private static int inputTryCount() {
        String inputTryCount = Console.readLine();

        try {
            int tryCount = Integer.parseInt(inputTryCount);

            if (tryCount <= 0) {
                throw new IllegalArgumentException("시도할 횟수는 1회 이상이어야 합니다.");
            }

            return tryCount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수는 숫자만 입력해 주세요.");
        }
    }

    private static void playRacing(List<Car> cars, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            for (Car car : cars) {
                int randomNumber = Randoms.pickNumberInRange(0, 9);
                car.move(randomNumber);
                System.out.println(car.getResult());
            }
            System.out.println();
        }
    }

    private static void printWinners(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }

        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }

        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

    public static class Car {

        private final String name;
        private int position = 0;

        public Car(String name) {
            validateCarName(name);
            this.name = name;
        }

        private void validateCarName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("자동차 이름을 입력해 주세요.");
            }

            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
            }
        }

        public void move(int randomNumber) {
            if (randomNumber >= 4) {
                position++;
            }
        }

        public String getResult() {
            return name + " : " + "-".repeat(position);
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
