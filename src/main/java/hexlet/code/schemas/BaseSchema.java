package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private Map<String, Predicate<Object>> checksNew = new LinkedHashMap<>();

    final void addCheck(String name, Predicate<Object> validate) {
        checksNew.put(name, validate);
    }

    public final boolean isValid(Object value) {
        for (String key : checksNew.keySet()) {
            if (!checksNew.get(key).test(value)) {
                return false;
            }
        }
        return true;
    }
}
