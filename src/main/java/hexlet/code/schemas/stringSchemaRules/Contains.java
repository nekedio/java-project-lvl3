package hexlet.code.schemas.stringSchemaRules;

import hexlet.code.schemas.Rule;

public final class Contains implements Rule {

    private String substring;

    public Contains(String line) {
        this.substring = line;
    }

    @Override
    public boolean isValid(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.contains(substring);
    }
}
