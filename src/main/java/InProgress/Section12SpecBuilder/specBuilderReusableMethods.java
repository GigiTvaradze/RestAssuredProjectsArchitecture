package InProgress.Section12SpecBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class specBuilderReusableMethods {

    public void requestSpec(){
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();
    }

    public void responseSpec(){
        ResponseSpecification resp = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("scope", equalTo("APP"))
                .expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
                .build();
    }
}
