package hexlet.code.schemas.numberSchemaRules;

import java.util.function.Predicate;

public final class Positive implements Predicate<Object> {
    @Override
    public boolean test(Object value) {
        if (value == null) {
            return true;
        }
        int num = (int) value;
        return num > 0;
    }
}
