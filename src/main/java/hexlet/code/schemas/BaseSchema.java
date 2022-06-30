package hexlet.code.schemas;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private Set<Predicate<Object>> checks = new HashSet<>();
    private Map<String, BaseSchema> nestedChecks = new LinkedHashMap<>();

    final void addCheck(Predicate<Object> validate) {
        checks.add(validate);
    }

    public final boolean isValid(Object value) {
        for (Predicate<Object> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }

    public final boolean isValid(Map<String, Object> map) {
        if (map == null) {
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

    final void addNestedCheck(String name, BaseSchema validate) {
        nestedChecks.put(name, validate);
    }
}
