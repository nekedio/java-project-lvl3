package hexlet.code.schemas.stringShemaRules;

import hexlet.code.schemas.Rule;

public final class Required implements Rule {

    @Override
    public boolean isValid(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.length() >= 1;
    }
}
