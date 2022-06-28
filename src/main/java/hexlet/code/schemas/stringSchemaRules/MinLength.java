package hexlet.code.schemas.stringSchemaRules;

import hexlet.code.schemas.Rule;

public final class MinLength implements Rule {

    private int minLength;

    public MinLength(int minLen) {
        this.minLength = minLen;
    }

    @Override
    public boolean isValid(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.length() >= minLength;
    }
}
