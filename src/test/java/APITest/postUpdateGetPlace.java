package APITest;

import JsonReader.JsonReader;
import client.RestAssuredClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class postUpdateGetPlace {
    String baseUrl = "https://rahulshettyacademy.com";
    RestAssuredClient client = new RestAssuredClient(baseUrl);

    @Test
    public void testPostRequest() throws IOException {
        String postRequestBody = JsonReader.readJsonFile("modul", ".json", "PostRequestBody1");
        Response response = client.post("maps/api/place/update/json", postRequestBody);
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
