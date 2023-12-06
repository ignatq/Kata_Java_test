package calculator;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private final Map<CalculatorType, Calculator> calculators;

    public Main() {
        calculators = new HashMap<>();
        calculators.put(CalculatorType.ROME, new RomeCalculator());
        calculators.put(CalculatorType.ARABIC, new ArabicCalculator());
    }

    public static String calc(String input) {
        return new Main().process(input);

    }

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.println("Для выхода наберите \"quit\"");
            System.out.print("введите пример:");
            String input = console.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                break;
            }
            try {
                System.out.println(calc(input));
            } catch (Exception e) {
                System.out.println("ОЙ! Произошла ошибка : \n" + e.getMessage());
//                throw e;
            }

        }
    }


    public String process(String input) {
        Calculator calculator = calculators.get(DataParser.checkInputDataAndGetCalcType(input));
        return calculator.calculate(DataParser.getCommandByCalcType(input.split(" "), calculator.type()));

    }
}