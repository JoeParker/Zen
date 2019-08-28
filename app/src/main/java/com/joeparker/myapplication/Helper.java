package com.joeparker.myapplication;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/* Helper class for useful common functions
 */


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

    //Write object to internal storage
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    //Read object from internal storage
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

}
