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

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        super.addCheck(
                "shape",
                value -> {
                    Map<String, Object> val = (Map<String, Object>) value;
                    for (String key : val.keySet()) {
                        if (!schemas.get(key).isValid(val.get(key))) {
                            return false;
                        }
                    }
                    return true;
                }
        );
        return this;
    }
}
