package hexlet.code;

import hexlet.code.schemas.StringSchema;

class App {
    public static void main(String[] args) {
        Validator v = new Validator();

        StringSchema schema = v.string();

        schema.isValid("");
    }
}
