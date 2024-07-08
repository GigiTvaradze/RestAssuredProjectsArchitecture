package InProgress.Section14CucumberBDD.StepDefinitions;

import Pojo.addPlacePojoClass;
import Pojo.childLocationPojoClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class stepDefinition {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private Response response;
    private String placeId;

    @Given("Add Place Payload")
    public void add_place_payload() {
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();

        addPlacePojoClass addPlace = new addPlacePojoClass();
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress("123 Main St");
        addPlace.setWebsite("http://www.google.com");
        addPlace.setLanguage("French-IN");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);

        childLocationPojoClass locationPojoClass = new childLocationPojoClass();
        locationPojoClass.setLat(-123456.987);
        locationPojoClass.setLng(123456.654);
        addPlace.setLocation(locationPojoClass);

        requestSpec = given()
                .spec(req)
                .body(addPlace);
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String apiName) {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("scope", equalTo("APP"))
                .expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
                .build();

        response = requestSpec
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .spec(responseSpec)
                .extract()
                .response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        Assert.assertEquals(value, response.jsonPath().getString(key));
    }
}
