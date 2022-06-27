package hexlet.code.schemas.stringShemaRules;

import hexlet.code.schemas.Rule;

public final class Contains implements Rule {

    private String substring;

    public Contains(String line) {
        this.substring = line;
    }

    @Override
    public boolean isValid(String line) {
        if (line == null) {
            return false;
        }

        return line.contains(substring);
    }
}
