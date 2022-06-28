package hexlet.code.schemas;

import hexlet.code.schemas.numberSchemaRules.Positive;
import hexlet.code.schemas.numberSchemaRules.Range;
import hexlet.code.schemas.numberSchemaRules.Required;

import java.util.HashSet;
import java.util.Set;

public final class NumberSchema {
    private final Set<Rule> checks = new HashSet<>();
    public NumberSchema required() {
        checks.add(new Required());
        return this;
    }

    public NumberSchema positive() {
        checks.add(new Positive());
        return this;
    }

    public NumberSchema range(int num1, int num2)  {
        checks.add(new Range(num1, num2));
        return this;
    }


    public boolean isValid(Object num) {
        if (num instanceof String) {
            return false;
        }

        for (Rule check : checks) {
            if (!check.isValid(num)) {
                return false;
            }
        }

        return true;
    }
}
