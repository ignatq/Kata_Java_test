package calculator;

public class ArabicCalculator implements Calculator {
    @Override
    public String calculate(Command command) {

        return  String.valueOf(defaultCalculate(command));
    }

    @Override
    public CalculatorType type() {
        return CalculatorType.ARABIC;
    }
}
