package calculator;

public enum MathOperation {

    SUM("+"),
    DIVISION(":"),
    MULTIPLY("*"),
    DIFFERENCE("-");

    private final String operator;

    MathOperation(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }


    public static MathOperation of(String key) {
        for (MathOperation operation : MathOperation.values()) {
            if (key != null && key.equals(operation.getOperator())) {
                return operation;
            }
        }
        throw new RuntimeException("Неизвестный математический оператор" +
                ", можно использовать только \"+\", \"-\", \"*\", \":\"");
    }
}
