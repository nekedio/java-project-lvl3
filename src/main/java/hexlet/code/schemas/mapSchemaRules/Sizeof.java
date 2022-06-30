package hexlet.code.schemas.mapSchemaRules;

import java.util.Map;
import java.util.function.Predicate;

public final class Sizeof implements Predicate<Object> {
    private final int count;

    public Sizeof(int mapCount) {
        this.count = mapCount;
    }

    @Override
    public boolean test(Object value) {
        Map map = (Map) value;
        return count == map.size();
    }
}
