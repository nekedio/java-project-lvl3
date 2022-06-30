package hexlet.code.schemas.stringSchemaRules;

import java.util.function.Predicate;

public final class MinLength implements Predicate<Object> {

    private int minLength;

    public MinLength(int minLen) {
        this.minLength = minLen;
    }

    @Override
    public boolean test(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.length() >= minLength;
    }
}
