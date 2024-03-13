import files.payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {

        //validate if "Add Place" is working as expected
        RestAssured.baseURI="https://rahulshettyacademy.com";

        given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)");

        //Add place --> Update Place with New Address --> Get Place to Validate if New Address is present in

    }

    //a418fd0106d716df7782f3238c206d02 id
}
