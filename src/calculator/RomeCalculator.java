package calculator;

public class RomeCalculator implements Calculator {
    @Override
    public String calculate(Command command) {
        int result = this.defaultCalculate(command);
        if (result < 0) {
            throw new RuntimeException("Не бывает отрицательный римских чисел");
        }
        return DataParser.parseArabicToRomeNumber(result);
    }

    @Override
    public CalculatorType type() {
        return CalculatorType.ROME;
    }
}
