package hexlet.code.schemas;

import hexlet.code.schemas.stringShemaRules.Contains;
import hexlet.code.schemas.stringShemaRules.MinLength;
import hexlet.code.schemas.stringShemaRules.Required;

public final class StringSchema extends Schema {

    public StringSchema required() {
        super.setChecks(new Required());
        return this;
    }

    public StringSchema contains(String substring) {
        super.setChecks(new Contains(substring));
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.setChecks(new MinLength(minLength));
        return this;
    }
}
