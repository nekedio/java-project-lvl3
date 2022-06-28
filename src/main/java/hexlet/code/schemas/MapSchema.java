package hexlet.code.schemas;

import hexlet.code.schemas.mapSchemaRules.Required;
import hexlet.code.schemas.mapSchemaRules.Sizeof;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MapSchema {
    private final Set<Rule> checks = new HashSet<>();

    public MapSchema required() {
        checks.add(new Required());
        return this;
    }

    public MapSchema sizeof(int count) {
        checks.add(new Sizeof(count));
        return this;
    }

    public boolean isValid(Map map) {
        for (Rule check : checks) {
            if (!check.isValid(map)) {
                return false;
            }
        }

        return true;
    }
}
