package com.joeparker.myapplication;

/* Helper class for useful common functions
 */

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Helper {

    //Get first map key from it's matching value
    public static Object getKeyFromValue(Map m, Object value) {
            Set<Map.Entry> entries = m.entrySet();
            for (Map.Entry entry : entries) {
                if (Objects.equals(value, entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null; //Returns null if no key pair is found for the value
    }


}
