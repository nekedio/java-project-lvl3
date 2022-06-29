package hexlet.code.schemas.numberSchemaRules;

import hexlet.code.schemas.Rule;

public final class Positive implements Rule {
    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return true;
        }
        int num = (int) value;
        return num > 0;
    }
}
