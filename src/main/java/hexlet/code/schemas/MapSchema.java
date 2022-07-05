package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

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

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            super.addNestedChecks(key, schemas.get(key));
        }
    }
}
