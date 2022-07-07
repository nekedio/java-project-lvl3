package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    final void addCheck(String name, Predicate<Object> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(Object value) {
        for (String key : checks.keySet()) {
            if (!checks.get(key).test(value)) {
                return false;
            }
        }
        return true;
    }
}
