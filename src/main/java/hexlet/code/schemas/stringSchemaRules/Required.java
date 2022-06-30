package hexlet.code.schemas.stringSchemaRules;

import java.util.function.Predicate;

public final class Required implements Predicate<Object> {

    @Override
    public boolean test(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.length() >= 1;
    }
}
