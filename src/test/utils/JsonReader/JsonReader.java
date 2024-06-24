package JsonReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileReader;

public class JsonReader {
    public static JSONObject readJsonFile(String resourcesDirectoryName, String fileName, String bodyName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            // Construct the file path
            String filePath = "src/test/resources/" + resourcesDirectoryName + "/" + fileName;

            // Parse the JSON file
            Object obj = parser.parse(new FileReader(filePath));

            // Convert object to JSONObject
            jsonObject = (JSONObject) obj;

            // Get the JSON object corresponding to the body name directly
            JSONObject body = (JSONObject) jsonObject.get(bodyName);

            if (body == null) {
                throw new NullPointerException("The requested body '" + bodyName + "' is null in the JSON file.");
            }

            return body;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error reading JSON file: " + e.getMessage());
        }
        return null;
    }
}
