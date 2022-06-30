package hexlet.code.schemas.numberSchemaRules;

import java.util.function.Predicate;

public final class Range implements Predicate<Object> {

    private int numBegin;
    private int numEng;

    public Range(int num1, int num2) {
        this.numBegin = num1;
        this.numEng = num2;
    }

    @Override
    public boolean test(Object value) {
        int num = (int) value;

        if (num < numBegin || num > numEng) {
            return false;
        }
        return true;
    }

}
