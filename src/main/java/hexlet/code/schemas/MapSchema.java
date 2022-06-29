package hexlet.code.schemas;

import hexlet.code.schemas.mapSchemaRules.Required;
import hexlet.code.schemas.mapSchemaRules.Sizeof;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MapSchema implements BaseSchema {
    private final Set<Rule> checks = new HashSet<>();

    private final Map<String, BaseSchema> mapChecks = new HashMap<>();

    public MapSchema required() {
        checks.add(new Required());
        return this;
    }

    public MapSchema sizeof(int count) {
        checks.add(new Sizeof(count));
        return this;
    }

    public boolean isValid(Object map) {
        if (!mapChecks.isEmpty()) {

            return isValidForShare((Map<String, Object>) map);

        }

        for (Rule check : checks) {
            if (!check.isValid(map)) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidForShare(Map<String, Object> map) {
        for (String key : map.keySet()) {
            BaseSchema schema = mapChecks.get(key);
            Object value = map.get(key);

            if (!schema.isValid(value)) {
                return false;
            }
        }
        return true;
    }


//    public boolean isValid(NullType value) {
//        System.out.println("++++++++++++++++++++++++++++++");
//        return false;
//    }

//    public boolean isValid(Map<String, Object> map) {
////        if (map == null) {
////            return false;
////        }
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        for (String key : map.keySet()) {
////            System.out.println(map.get(key));
////            System.out.println(mapChecks.get(key).isValid(map.get(key)));
//            BaseSchema schema = mapChecks.get(key);
//            Object value = map.get(key);
//            System.out.println("---------------");
//            System.out.println(schema);
//            System.out.println(value);
////            System.out.println(schema.isValid(value));
//            System.out.println("---------------");
////            System.out.println(schema.isValid());
////            System.out.println(mapChecks.get(key));
////            System.out.println(value.isValid());
//            if (!schema.isValid(value)) {
//                return false;
//            }
//
//        }
//
//        return true;
//    }

    public void shape(Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            mapChecks.put(key, schemas.get(key));
        }

//        System.out.println(mapChecks.toString());
    }
}
