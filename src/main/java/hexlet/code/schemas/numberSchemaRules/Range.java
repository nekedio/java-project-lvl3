package hexlet.code.schemas.numberSchemaRules;

import hexlet.code.schemas.Rule;

public final class Range implements Rule {

    private int numBegin;
    private int numEng;

    public Range(int num1, int num2) {
        this.numBegin = num1;
        this.numEng = num2;
    }

    @Override
    public boolean isValid(Object value) {
        int num = (int) value;

        if (num < numBegin || num > numEng) {
            return false;
        }
        return true;
    }

}
