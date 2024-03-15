package client;

//Client:includes general api call methods to avoid retyping the same codes in our step classes or helper classes.

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {
    String baseUrl;

    public RestAssuredClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Response get(String path){
        return createRequest()
                .get(path);
    }

    public Response post(String path, Object body){
        return createRequest()
                .body(body)
                .post(path);
    }

    public Response put(String path, Object body){
        return createRequest()
                .body(body)
                .put(path);
    }

    public Response delete(String path){
        return createRequest()
                .delete(path);
    }

    private RequestSpecification createRequest() {
        return RestAssured.given()
                .log()
                .all(true)
                .baseUri(baseUrl)
                .header("Content-Type", "application/json");
    }
}

