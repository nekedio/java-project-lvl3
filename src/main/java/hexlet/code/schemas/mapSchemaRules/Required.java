package hexlet.code.schemas.mapSchemaRules;

import hexlet.code.schemas.Rule;

public final class Required implements Rule {
    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }
        return true;
    }
}
