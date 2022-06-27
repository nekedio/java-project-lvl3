package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;


import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.assertj.core.condition.Negative;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void testString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.isValid("hexlet")).isTrue(); // true
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse();; // false

        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse(); // false

        assertThat(schema.isValid("what does the fox say")).isFalse(); // false

        assertThat(schema.minLength(10).isValid("whatthe")).isFalse(); //false
        assertThat(schema.isValid("whattheqqqq")).isTrue(); //true
        assertThat(schema.minLength(2).isValid("qqq")).isFalse(); //false
    }

    @Test
    public void testNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(10)).isTrue(); // true
        assertThat(schema.isValid("5")).isFalse(); // false

        assertThat(schema.positive().isValid(10)).isTrue(); // true
        assertThat(schema.isValid(-10)).isFalse(); // false

        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue(); // true
        assertThat(schema.isValid(10)).isTrue(); // true
        assertThat(schema.isValid(4)).isFalse(); // false
        assertThat(schema.isValid(11)).isFalse(); // false
    }
}
