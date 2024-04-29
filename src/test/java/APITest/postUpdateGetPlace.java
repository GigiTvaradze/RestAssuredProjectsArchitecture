package APITest;

import JsonReader.JsonReader;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.jsonfile.JsonFile;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PositiveCases {
    @BeforeMethod
    public void setUp() {
        // Setup tasks, such as initializing variables, opening connections, etc.
        RestAssured.baseURI = "";
    }

    @Test(priority = 16,description = "")
    public void authorizationEntityWithIdWasNotFound() {

        JSONObject body = JsonReader.readJsonFile(
                "RefundFromPosJsonFiles",
                "PositiveCases.json",
                "test16");


        // Configure RestAssured to relax SSL certificate validation
        RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation());

        String response = given()
                .header("Content-Type", "application/json")
                .body(body)

                .when()
                .post("refunds/pos")

                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

//        JsonPath js = new JsonPath(response);
//        String requestAnswer = js.getString("");
//        System.out.println(requestAnswer);
//        Assert.assertEquals(requestAnswer,"Success","Request Is Successful");
    }

    @AfterMethod
    public void tearDown() {
        // Perform teardown tasks here, such as closing connections or releasing resources
        System.out.println("Teardown tasks after each test method");
    }
}
