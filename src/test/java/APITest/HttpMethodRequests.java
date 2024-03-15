package APITest;

import JsonReader.JsonReader;
import client.RestAssuredClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpMethodRequests {
    String baseUrl = "";
    RestAssuredClient client = new RestAssuredClient(baseUrl);

    @Test
    public void testGetRequest() {
        Response response = client.get("");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testPostRequest() throws IOException {
        String postRequestBody = JsonReader.readJsonFile("modul", ".json", "PostRequestBody1");
        Response response = client.post("/", postRequestBody);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void testPutRequest() throws IOException {
        String putRequestBody = JsonReader.readJsonFile("modul2", "P.json", "requestBody2");
        Response response = client.put("", putRequestBody);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
    }

    @Test
    public void testDeleteRequest() {
        Response response = client.delete("");
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
