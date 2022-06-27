package hexlet.code.schemas;

import hexlet.code.schemas.stringShemaRules.Contains;
import hexlet.code.schemas.stringShemaRules.MinLength;
import hexlet.code.schemas.stringShemaRules.Required;

import java.util.HashSet;
import java.util.Set;

public final class StringSchema {

    private final Set<Rule> checks = new HashSet<>();

    public StringSchema required() {
        checks.add(new Required());
        return this;
    }

    public StringSchema contains(String substring) {
        checks.add(new Contains(substring));
        return this;
    }

    public StringSchema minLength(int minLength) {
        checks.add(new MinLength(minLength));
        return this;
    }


    public boolean isValid(String line) {

        for (Rule check : checks) {
            if (!check.isValid(line)) {
                return false;
            }
        }

        return true;
    }
}
