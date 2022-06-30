package hexlet.code.schemas;

import hexlet.code.schemas.mapSchemaRules.Required;
import hexlet.code.schemas.mapSchemaRules.Sizeof;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        super.addCheck(new Required());
        return this;
    }

    public MapSchema sizeof(int count) {
        super.addCheck(new Sizeof(count));
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            super.addNestedCheck(key, schemas.get(key));
        }
    }
}
