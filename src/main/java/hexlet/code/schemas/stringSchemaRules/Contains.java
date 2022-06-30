package hexlet.code.schemas.stringSchemaRules;

//import hexlet.code.schemas.Rule;
import java.util.function.Predicate;


public final class Contains implements Predicate<Object> {

    private String substring;

    public Contains(String line) {
        this.substring = line;
    }

    @Override
    public boolean test(Object value) {
        String line = (String) value;
        if (line == null) {
            return false;
        }

        return line.contains(substring);
    }
}
