package InProgress.Section12SpecBuilder;

import InProgress.ReUsableMethods;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Section12SpecBuilder {

    //RequestSpecBuilder class
    //req = new RequestSpecBuilder(). ( things which can be commonly used across all test cases ).build() method;
    //given().spec(<SpecBuilderObject>)  >>>  given().spec(req)

    //ResponseSpecBuilder class
    //resp = new RequestSpecBuilder(). ( things which can be commonly used across all test cases ).build() method;
    //then().spec(resp).extract().response();

    @Test
    public void specBuilder() {

        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();

        ResponseSpecification resp = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("scope", equalTo("APP"))
                .expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
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

        String Response = given()
                .spec(req) //Request spec builder
                .body(addPlace)
                .when()
                .post("maps/api/place/add/json")
                .then()
                .spec(resp)
                .extract().response().asString();

        System.out.println(Response);

        JsonPath js = ReUsableMethods.rawToJson(Response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);
        Assert.assertNotNull(placeId);
    }
}
