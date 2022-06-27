package hexlet.code.schemas.stringShemaRules;

import hexlet.code.schemas.Rule;

public final class Required implements Rule {

    @Override
    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }

        return line.length() >= 1;
    }
}
