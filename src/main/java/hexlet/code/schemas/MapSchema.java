package hexlet.code.schemas;

import hexlet.code.schemas.mapSchemaRules.Required;
import hexlet.code.schemas.mapSchemaRules.Sizeof;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private final Map<String, BaseSchema> nestedChecks = new LinkedHashMap<>();

    public MapSchema required() {
        super.addCheck(new Required());
        return this;
    }

    public MapSchema sizeof(int count) {
        super.addCheck(new Sizeof(count));
        return this;
    }

    public boolean isValid(Map<String, Object> map) {
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

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            nestedChecks.put(key, schemas.get(key));
        }
    }
}
