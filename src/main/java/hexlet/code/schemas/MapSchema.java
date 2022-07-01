package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private final Map<String, BaseSchema> nestedChecks = new LinkedHashMap<>();

    public MapSchema required() {
        super.addCheck(
                "required",
                value -> value instanceof Map
        );
        return this;
    }

    public MapSchema sizeof(int count) {
        super.addCheck(
                "sizeof(" + count + ")",
                value -> {
                    Map map = (Map) value;
                    return count == map.size();
                }
        );
        return this;
    }

    public boolean isValid(Map<String, Object> map) {
        if (map == null || map.size() == 0) {
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

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            nestedChecks.put(key, schemas.get(key));
        }
    }
}
