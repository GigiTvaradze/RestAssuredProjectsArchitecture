package InProgress;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class Section5ValidationApiResponses extends BaseTest{
    @Test
    public void validateAddPlace() {
        // Your test code here

        String Response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                //FOR NEXT --> call from json file
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                //Add place --> Update Place with New Address --> Get Place to Validate if New Address is present in
                //Parsing the Json Response  body using JsonPath class
                .extract().response().asString();

        System.out.println(Response);

        //JsonPath js = new JsonPath(Response); //for parsing Json
        JsonPath js = ReUsableMethods.rawToJson(Response);
        String placeId = js.getString("place_id");

        System.out.println(placeId);

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"place_id\": \"" + placeId + "\",\n" +
                        "  \"address\": \"44 winter walk walk, GEO\",\n" +
                        "  \"key\": \"qaclick123\"\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .statusCode(200).log().all()
                .body("msg", equalTo("Address successfully updated"));

        //Verifying the edited data
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .header("Content-Type", "application/json")
                .when()
                .get("maps/api/place/get/json")
                .then().assertThat()
                .statusCode(200).log().all()
                .extract().response().asString();


        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");

        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, "44 winter walk walk, GEO", "Address is Different !!! ");
    }
}