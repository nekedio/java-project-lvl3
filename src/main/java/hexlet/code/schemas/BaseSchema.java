package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    private final Map<String, BaseSchema> nestedChecks = new LinkedHashMap<>();

    final void addCheck(String name, Predicate<Object> validate) {
        checks.put(name, validate);
    }

    final void addNestedChecks(String key, BaseSchema schema) {
        nestedChecks.put(key, schema);
    }

    public final boolean isValid(Object value) {
        for (String key : checks.keySet()) {
            if (!checks.get(key).test(value)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isValid(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return isValid((Object) map);
        }

        for (String key : map.keySet()) {
            BaseSchema schema = nestedChecks.get(key);
            Object value = map.get(key);

            if (!schema.isValid(value)) {
                return false;
            }
        }

        return true;
    }
}
