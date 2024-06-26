package InProgress;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Section9HandlingOathAuthorizationGrantType extends BaseTest {
    @Test
    public static void handlingOauthAuthorizationGrantType() {
        // Client Credentials Grant type > access_token

        // Service for access_token
        // Confidential, secure information always must send with POST method
        String tokenResponse = given().log().all()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when()
                .log().all()
                .post("/oauthapi/oauth2/resourceOwner/token")
                .then().statusCode(200).extract().asString();

        System.out.println("Token Response: " + tokenResponse);

        JsonPath js = ReUsableMethods.rawToJson(tokenResponse);
        String accToken = js.getString("access_token");
        System.out.println("Access Token: " + accToken);

        if (accToken == null || accToken.isEmpty()) {
            System.out.println("Failed to obtain access token");
            return;
        }

        String response = given().queryParam("access_token", accToken)
                .when()
                .log().all()
                .get("/oauthapi/getCourseDetails")
                .then()
                .log().all()
                .assertThat()
                .statusCode(401).extract().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(response);
        String instructor = js1.getString("instructor");
        Assert.assertEquals(instructor, "RahulShetty");
    }
}
