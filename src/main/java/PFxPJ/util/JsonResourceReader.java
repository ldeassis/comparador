package PFxPJ.util;

import org.json.*;
import java.io.*;

public class JsonResourceReader {
    private JSONObject jsonObject;

    /**
     * A utility class for reading JSON resources from the classpath.
     * @param resourcePath the path to the JSON resource
     * @throws Exception if the resource is not found
     */
    public JsonResourceReader(String resourcePath) throws Exception {
        InputStream is = JsonResourceReader.class.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new FileNotFoundException("Resource not found!!!: " + resourcePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonObj = new JSONObject(sb.toString());
        this.jsonObject = jsonObj;
    }

    /**
     * Returns a string representation of the JSON object.
     *
     * @return a string representation of the JSON object.
     */
    @Override
    public String toString() {
        return jsonObject.toString();
    }

    /**
     * Returns the JSONObject associated with this JsonResourceReader.
     *
     * @return the JSONObject associated with this JsonResourceReader
     */
    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
