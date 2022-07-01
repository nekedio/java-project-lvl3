package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        super.addCheck(
                "required",
                value -> value instanceof Integer
        );
        return this;
    }

    public NumberSchema positive() {
        super.addCheck(
                "positive",
                value -> !(value instanceof Integer) || (int) value > 0
        );
        return this;
    }

    public NumberSchema range(int num1, int num2)  {
        super.addCheck(
                "range",
                value -> (int) value >= num1 && (int) value <= num2
        );
        return this;
    }
}
