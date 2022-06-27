package hexlet.code.schemas.stringShemaRules;

import hexlet.code.schemas.Rule;

public final class MinLength implements Rule {

    private int minLength;

    public MinLength(int minLen) {
        this.minLength = minLen;
    }

    @Override
    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }

        return line.length() >= minLength;
    }
}
