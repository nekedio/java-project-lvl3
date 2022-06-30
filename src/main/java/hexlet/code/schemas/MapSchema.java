package hexlet.code.schemas;

import hexlet.code.schemas.mapSchemaRules.Required;
import hexlet.code.schemas.mapSchemaRules.Sizeof;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapSchema implements BaseSchema {
    private final Set<Predicate<Object>> checks = new HashSet<>();

    private final Map<String, BaseSchema> mapChecks = new HashMap<>();

    public MapSchema required() {
        checks.add(new Required());
        return this;
    }

    public MapSchema sizeof(int count) {
        checks.add(new Sizeof(count));
        return this;
    }

    public boolean isValid(Object value) {
        for (Predicate<Object> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }

    public boolean isValid(Map<String, Object> map) {
        if (map == null) {
            return isValid((Object) map);
        }

        for (String key : map.keySet()) {
            BaseSchema schema = mapChecks.get(key);
            Object value = map.get(key);

            if (!schema.isValid(value)) {
                return false;
            }
        }

        return true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            mapChecks.put(key, schemas.get(key));
        }
    }
}
