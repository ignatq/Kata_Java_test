package calculator;

public interface Calculator {

    String calculate(Command command);
    default int defaultCalculate(Command command) {
        return switch (command.getMathOperation()) {
            case SUM -> command.getFirstNumber() + command.getSecondNumber();
            case DIFFERENCE -> command.getFirstNumber() - command.getSecondNumber();
            case MULTIPLY -> command.getFirstNumber() * command.getSecondNumber();
            case DIVISION -> command.getFirstNumber() / command.getSecondNumber();
        };
    }

    CalculatorType type();
}
