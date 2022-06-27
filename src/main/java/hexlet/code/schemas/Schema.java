package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;

abstract class Schema {

    private final Set<Rule> checks = new HashSet<>();

    public void setChecks(Rule checkRule) {
        this.checks.add(checkRule);
    }

    public boolean isValid(String line) {
        for (Rule check : checks) {
            if (!check.isValid(line)) {
                return false;
            }
        }

        return true;
    }
}
