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
    public void testString() {
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
        assertThat(schema.minLength(Integer.parseInt("10")).isValid("whatthe")).isFalse();
        assertThat(schema.isValid("whattheqqqq")).isTrue();
        assertThat(schema.minLength(Integer.parseInt("2")).isValid("qqq")).isFalse();
    }

    @Test
    public void testNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();

        /*
         * test required
         */
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(Integer.parseInt("10"))).isTrue();
        assertThat(schema.isValid("5")).isFalse();

        /*
         * test positive
         */
        assertThat(schema.positive().isValid(Integer.parseInt("10"))).isTrue();
        assertThat(schema.isValid(Integer.parseInt("-10"))).isFalse();

        /*
         * test range
         */
        schema.range(Integer.parseInt("5"), Integer.parseInt("10"));

        assertThat(schema.isValid(Integer.parseInt("5"))).isTrue();
        assertThat(schema.isValid(Integer.parseInt("10"))).isTrue();
        assertThat(schema.isValid(Integer.parseInt("4"))).isFalse();
        assertThat(schema.isValid(Integer.parseInt("11"))).isFalse();
    }

    @Test
    public void testMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();

        /*
         * test required
         */
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();

        /*
         * test sizeof
         */
        schema.sizeof(Integer.parseInt("2"));

        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();

    }

    @Test
    public void testNestedMap() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", Integer.parseInt("100"));
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", Integer.parseInt("-5"));
        assertThat(schema.isValid(human4)).isFalse();
    }

    @Test
    public void testMapValidator() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();

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
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", Integer.parseInt("100"));
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
        actual5.put("age", Integer.parseInt("-5"));
        assertThat(schema.isValid(actual5)).isFalse();

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", Integer.parseInt("15"));
        assertThat(schema.isValid(actual6)).isFalse();
    }
}
