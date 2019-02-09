package utility;

import com.google.gson.Gson;

public class TestUtils {

    public static String getObjectToJson(Object object){
        return  new Gson().toJson(object);
    }

    public static Object getJsonToObject() {
        return null;
    }
}
