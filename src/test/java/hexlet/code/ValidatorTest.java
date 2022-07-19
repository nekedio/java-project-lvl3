package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {

    @Test
    public void testStringValidator() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        /*
         * test required
         */
        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();

        /*
         * test contains
         */
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

        /*
         * test minLength
         */
        assertThat(schema.minLength(10).isValid("whatthe")).isFalse();
        assertThat(schema.isValid("whattheqqqq")).isTrue();
        assertThat(schema.minLength(2).isValid("qqq")).isFalse();
    }

    @Test
    public void testNumberValidator() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();

        /*
         * test required
         */
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid("5")).isFalse();

        /*
         * test positive
         */
        assertThat(schema.positive().isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();

        /*
         * test range
         */
        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    public void testMapValidator() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap())).isTrue();

        /*
         * test required
         */
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();

        /*
         * test sizeof
         */
        schema.sizeof(2);
        assertThat(schema.isValid(new HashMap())).isFalse();
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schemas.put("age", v.number().positive());

        /*
         * test nested map
         */
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", 100);
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isTrue();

        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertThat(schema.isValid(actual4)).isFalse();

        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", -5);
        assertThat(schema.isValid(actual5)).isFalse();

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", 15);
        assertThat(schema.isValid(actual6)).isFalse();
    }
}
