package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private Set<Predicate<Object>> checks = new HashSet<>();

    final void addCheck(Predicate<Object> validate) {
        checks.add(validate);
    }

    public final boolean isValid(Object value) {
        for (Predicate<Object> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}
