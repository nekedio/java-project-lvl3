package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        super.addCheck(
                "required",
                value -> value instanceof String && !value.toString().equals("")
        );
        return this;
    }

    public StringSchema contains(String substring) {
        super.addCheck(
                "contains",
                value -> value.toString().contains(substring)
        );
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addCheck(
                "minLength",
                value -> value.toString().length() >= minLength
        );
        return this;
    }
}
