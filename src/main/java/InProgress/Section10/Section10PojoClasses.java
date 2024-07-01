package InProgress.Section10;

import InProgress.BaseTest;
import InProgress.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Section10PojoClasses extends BaseTest {


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

        mainPojoClasses getCourseDetails = given().queryParam("access_token", accToken)
                .when()
                .log().all()
                .get("/oauthapi/getCourseDetails")
                .then()
                .log().all()
                .assertThat()
                .statusCode(401).extract().as(mainPojoClasses.class); //Returning Java Class Object

        Assert.assertEquals(getCourseDetails.getInstructor(), "RahulShetty");
        Assert.assertEquals(getCourseDetails.getLinkedIn(),"https://www.linkedin.com/in/rahul-shetty-trainer/");

        //Total price of Courses
        System.out.println(getCourseDetails.getCourses().getApi().get(0).getPrice());
        System.out.println(getCourseDetails.getCourses().getApi().get(1).getPrice());
    }
}
