package calculator;

import java.util.*;

public class DataParser {

    private static final Map<String, Integer> romeToArabicDigits = new HashMap<>();

    static {
        romeToArabicDigits.put("I", 1);
        romeToArabicDigits.put("II", 2);
        romeToArabicDigits.put("III", 3);
        romeToArabicDigits.put("IV", 4);
        romeToArabicDigits.put("V", 5);
        romeToArabicDigits.put("VI", 6);
        romeToArabicDigits.put("VII", 7);
        romeToArabicDigits.put("VIII", 8);
        romeToArabicDigits.put("IX", 9);
        romeToArabicDigits.put("X", 10);
    }

    private static final Map<Integer, String> arabicToRomeDigits = new HashMap<>();

    static {
        arabicToRomeDigits.put(1, "I");
        arabicToRomeDigits.put(2, "II");
        arabicToRomeDigits.put(3, "III");
        arabicToRomeDigits.put(4, "IV");
        arabicToRomeDigits.put(5, "V");
        arabicToRomeDigits.put(6, "VI");
        arabicToRomeDigits.put(7, "VII");
        arabicToRomeDigits.put(8, "VIII");
        arabicToRomeDigits.put(9, "IX");
        arabicToRomeDigits.put(10, "X");
        arabicToRomeDigits.put(20, "XX");
        arabicToRomeDigits.put(30, "XXX");
        arabicToRomeDigits.put(40, "XL");
        arabicToRomeDigits.put(50, "L");
        arabicToRomeDigits.put(60, "LX");
        arabicToRomeDigits.put(70, "LXX");
        arabicToRomeDigits.put(80, "LXXX");
        arabicToRomeDigits.put(90, "XC");
        arabicToRomeDigits.put(100, "C");
    }

    static Set<String> mathOperations = new HashSet<>();

    static {
        mathOperations.add("+");
        mathOperations.add("-");
        mathOperations.add("*");
        mathOperations.add("/");
    }

    public static CalculatorType checkInputDataAndGetCalcType(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length != 3) {
            throw new RuntimeException("Неправильный пример(формат:\"a operator b\")");
        }
        String firstNumber = inputArray[0];
        String secondNumber = inputArray[2];
        if (romeToArabicDigits.containsKey(firstNumber) && romeToArabicDigits.containsKey(secondNumber)) {
            return CalculatorType.ROME;
        } else if (romeToArabicDigits.containsKey(firstNumber) || romeToArabicDigits.containsKey(secondNumber)) {
            throw new RuntimeException("Можно использовать только однотипные числа");
        }
        int number1 = Integer.parseInt(firstNumber);
        int number2 = Integer.parseInt(secondNumber);
        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            throw new NumberFormatException("Доступен диапазон от 1 до 10");
        }
        return CalculatorType.ARABIC;
    }

    public static Command getCommandByCalcType(String[] inputArray, CalculatorType calculatorType) {
        Command command = new Command();
        command.setMathOperation(MathOperation.of(inputArray[1]));
        return switch (calculatorType) {
            case ARABIC -> {
                command.setFirstNumber(Integer.parseInt(inputArray[0]));
                command.setSecondNumber(Integer.parseInt(inputArray[2]));
                yield command;
            }
            case ROME -> {
                command.setFirstNumber(romeToArabicDigits.get(inputArray[0]));
                command.setSecondNumber(romeToArabicDigits.get(inputArray[2]));
                yield command;
            }
        };
    }


    public static String parseArabicToRomeNumber(int arabicNumber) {
        int dividedArabicNumber = arabicNumber / 10;
        int remainderOfDivision = arabicNumber % 10;
        if (dividedArabicNumber < 1) {
            return arabicToRomeDigits.get(arabicNumber);
        } else if (dividedArabicNumber < 10 && remainderOfDivision == 0) {
            return arabicToRomeDigits.get(dividedArabicNumber * 10);
        } else {
            return arabicToRomeDigits.get(dividedArabicNumber * 10) +
                    arabicToRomeDigits.get(remainderOfDivision);
        }
    }
}
