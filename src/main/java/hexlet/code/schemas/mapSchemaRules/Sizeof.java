package hexlet.code.schemas.mapSchemaRules;

import hexlet.code.schemas.Rule;

import java.util.Map;

public final class Sizeof implements Rule {
    private final int count;

    public Sizeof(int mapCount) {
        this.count = mapCount;
    }

    @Override
    public boolean isValid(Object value) {
        Map map = (Map) value;
        return count == map.size();
    }
}
