package json;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static String toJson(Object object) {
        final GsonBuilder gson = new GsonBuilder();
        gson.excludeFieldsWithoutExposeAnnotation();
        return gson.create().toJson(object);
    }

}
