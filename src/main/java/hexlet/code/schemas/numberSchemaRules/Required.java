package hexlet.code.schemas.numberSchemaRules;

import hexlet.code.schemas.Rule;

public final class Required implements Rule {


    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
