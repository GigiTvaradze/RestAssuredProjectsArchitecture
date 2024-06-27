package InProgress;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

//Send File Attachments Through Rest API Calls
//Jira API to create bug and attach failed Screenshots through REST APIs

public class Section8AutomatingJiraAPI {
    @Test(dataProvider = "bugVersions")
    public void jiraBugCreation(String summaryText,String contentDescriptionText,String APIToken){
        RestAssured.baseURI = "https://gtvaradze.atlassian.net/";

        //Create Bug
        String Response =
                given()
                .header("Authorization", "Basic Z3R2YXJhZHplMjRAZ21haWwuY29tOkFUQVRUM3hGZkdGMEVqWjBzdzhBbnRsSmtyU1lrU3dscnVLbGs4Rk1xUWNYaVV3eGhlSUF5eFRpUEkxbjc1RjY5dW82M25vaklLUmVtbUNRNlFwZHhqTmFMdWRnU1gwWWNKaV84TnNPN2lSbUxQNVI0eGxIaTJmaGFsT283NUVhSERnbW54UG90b04xeDJuSFFuaWZLN09xS3BoMnB5X2t6T09ETU1TaFB2d3BWa1o5VzRLZEFvWT0xQTVGNEUwNA==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload.jiraCreateIssueData(summaryText,contentDescriptionText))
                        .log()
                        .all()
                .when()
                .post("rest/api/3/issue")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .extract()
                .asString();

        JsonPath js = ReUsableMethods.rawToJson(Response);
        String id = js.getString("id");
        String key = js.getString("key");
        String self = js.getString("self");

        System.out.println(id);
        System.out.println(key);
        System.out.println(self);

        //attach the screenshot
        String attachedFileOnIssue =
                given().pathParam("id",id)
                .header("Authorization", "Basic "+ APIToken)
                .header("X-Atlassian-Token", "no-check")
                //file attachment:
                .multiPart("file",new File("/Users/gtv/Desktop/Momavali.png")).log().all()
                .when()
                .post("rest/api/3/issue/{id}/attachments")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();

        JsonPath js2 = ReUsableMethods.rawToJson(attachedFileOnIssue);

        String fileName = js2.getString("filename");
        Assert.assertNotNull(fileName,"fileName is null");
    }

    @DataProvider(name = "bugVersions")
    public Object[][] getBugVersions() {
        return new Object[][]{
                {"Improve User Authentication Security","Implementing multi-factor authentication (MFA)"},
                {"Optimize Database Performance for Large Queries","Implement caching solutions where applicable"},
                {"Redesign Homepage for Better User Engagement","Implement A/B testing to measure the impact of changes"}
        };
    }
}


