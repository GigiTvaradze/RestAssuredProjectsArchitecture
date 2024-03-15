package JsonReader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
    public static String readJsonFile(String module, String fileName, String requestBodyName) throws IOException {
        String content = null;
        String filePath = "/json/" + module + "/" + fileName;
        try (InputStream inputStream = JsonReader.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // Read the JSON file content into a string
            String jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));

            // Parse the JSON string to create a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Get the specific request body by name
            JSONObject postRequestBodies = jsonObject.getJSONObject(requestBodyName);

            // Convert the JSONObject to a JSON string
            content = postRequestBodies.toString();
        } catch (JSONException e) {
            // Handle JSON parsing errors
            e.printStackTrace(); // Or log the error message
        }
        return content;
    }
}
