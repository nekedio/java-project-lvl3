package hexlet.code.schemas.mapSchemaRules;

import java.util.function.Predicate;

public final class Required implements Predicate<Object> {
    @Override
    public boolean test(Object value) {
        return value != null;
    }
}
