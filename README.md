### Hexlet tests and linter status:
[![Actions Status](https://github.com/nekedio/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/nekedio/java-project-lvl3/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/2610e8bec79eb4b6f9ae/maintainability)](https://codeclimate.com/github/nekedio/java-project-lvl3/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2610e8bec79eb4b6f9ae/test_coverage)](https://codeclimate.com/github/nekedio/java-project-lvl3/test_coverage)
[![tests](https://github.com/nekedio/java-project-lvl3/actions/workflows/tests.yml/badge.svg)](https://github.com/nekedio/java-project-lvl3/actions/workflows/tests.yml)


## Validation of strings

required  
contains(\<line\>)  
minLength(\<min_string_length\>)  

```sh
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid(null); // false
schema.isValid("");; // false

schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false

schema.minLength(10)).isValid("whatthe") // false
schema.isValid("whattheqqqq")) // true

```
[![asciicast](https://asciinema.org/a/507720.svg)](https://asciinema.org/a/507720)

## Validation of numbers

required  
positive  
range(\<from\>, \<to\>)  

```sh
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false

```

## Validation of Map type objects

required  
sizeof  

```sh
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

```

## Nested validation
```sh
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false

```

