package InProgress.Section10;

import InProgress.BaseTest;
import InProgress.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Section10PojoClasses extends BaseTest {


    @Test
    public static void deserializationExample() {
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

//        System.out.println(getCourseDetails.getCourses().getApi().get(0).getPrice());
//        System.out.println(getCourseDetails.getCourses().getApi().get(1).getPrice());

        //Get Price of SoapUI Webservices testing course dynamically
        List<api> apiCourses = getCourseDetails.getCourses().getApi();
        for (int i = 0; i < apiCourses.size(); i++) {
           if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }
    }


    @Test(description = " Sum of all Course prices")
    public void sumOfCourses(){

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

        //getCourseDetails.getCourses().getApi().get(i).getCourseTitle();
        //getCourseDetails.getCourses().getMobile().get(i).getCourseTitle();
        //getCourseDetails.getCourses().getWebAutomation().get(i).getCourseTitle();

        List<api> apiCourses = getCourseDetails.getCourses().getApi();
        int apiSum = 0;
        for (int i = 0; i < apiCourses.size(); i++) {
            int coursesPrice = Integer.parseInt(apiCourses.get(i).getPrice());
            //System.out.println(coursesPrice);
            apiSum += coursesPrice;
            System.out.println(apiSum);
        }

        List<mobile> mobilesCourses = getCourseDetails.getCourses().getMobile();
        int mobileSum = 0;
        for (int j = 0; j < mobilesCourses.size(); j++) {
            int coursesPrice = Integer.parseInt(mobilesCourses.get(j).getPrice());
            //System.out.println(coursesPrice);
            mobileSum += coursesPrice;
            System.out.println(mobileSum);
        }

        List<webAutomation> webAutomationCourses = getCourseDetails.getCourses().getWebAutomation();
        int  webAutomationSum= 0;
        for (int z = 0; z < webAutomationCourses.size(); z++) {
            int coursesPrice = Integer.parseInt(webAutomationCourses.get(z).getPrice());
            webAutomationSum += coursesPrice;
            System.out.println(webAutomationSum);
        }

        int totalCoursesPrice = apiSum + mobileSum + webAutomationSum;
        System.out.println(totalCoursesPrice);
    }



    @Test(description = "Get the Course Names Of WebAutomation")
    public void getCourseNamesOfWebAutomation() {
        String [] courseTitles = {"Selenium Webdriver Java" , "Cypress" , "Protractor"};

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


        ArrayList<String> actualList = new ArrayList<String>();

        List <webAutomation> webAutomationCourses = getCourseDetails.getCourses().getWebAutomation();
        for (int i = 0; i < webAutomationCourses.size(); i++) {
            actualList.add(webAutomationCourses.get(i).getCourseTitle());
        }

        //compare array to arraylist
        //convert array to arraylist, to compare
        List<String> expectedList = Arrays.asList(courseTitles);

        Assert.assertEquals(expectedList, actualList);
    }
}
