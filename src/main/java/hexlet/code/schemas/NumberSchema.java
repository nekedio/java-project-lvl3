package hexlet.code.schemas;

import hexlet.code.schemas.numberSchemaRules.Positive;
import hexlet.code.schemas.numberSchemaRules.Range;
import hexlet.code.schemas.numberSchemaRules.Required;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        super.addCheck(new Required());
        return this;
    }

    public NumberSchema positive() {
        super.addCheck(new Positive());
        return this;
    }

    public NumberSchema range(int num1, int num2)  {
        super.addCheck(new Range(num1, num2));
        return this;
    }
}
