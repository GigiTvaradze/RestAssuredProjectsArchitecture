package InProgress;

import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReUsableMethods {

    public static JsonPath rawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
