package hexlet.code.schemas;

import hexlet.code.schemas.stringSchemaRules.Contains;
import hexlet.code.schemas.stringSchemaRules.MinLength;
import hexlet.code.schemas.stringSchemaRules.Required;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        super.addCheck(new Required());
        return this;
    }

    public StringSchema contains(String substring) {
        super.addCheck(new Contains(substring));
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addCheck(new MinLength(minLength));
        return this;
    }
}
