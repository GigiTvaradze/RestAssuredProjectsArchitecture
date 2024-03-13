import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Basics {
    public static void main(String[] args) {

        //validate if "Add Place" is working as expected
        RestAssured.baseURI="https://rahulshettyacademy.com";

        String Response = given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                //FOR NEXT --> call from json file
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
        //Add place --> Update Place with New Address --> Get Place to Validate if New Address is present in
        //Parsing the Json Response  body using JsonPath class
                .extract().response().asString();

        System.out.println(Response);

        JsonPath js = new JsonPath(Response); //for parsing Json
        String placeId =  js.getString("place_id");

        System.out.println(placeId);

        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
//              .body(payload.UpdateAddress())
                .body("{\n" +
                        "  \"place_id\": \""+placeId+"\",\n" +
                        "  \"address\": \"44 winter walk walk, GEO\",\n" +
                        "  \"key\": \"qaclick123\"\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .statusCode(200).log().all()
                .body("msg",equalTo("Address successfully updated"));

        //Verifying the edited data
        String getPlaceResponse = given().log().all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .header("Content-Type","application/json")
                .when()
                .get("maps/api/place/get/json")
                .then().assertThat()
                .statusCode(200).log().all()
                .extract().response().asString();

        JsonPath js1 = new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");

        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress,"44 winter walk walk, GEO","Address is Different !!! ");
    }
}
