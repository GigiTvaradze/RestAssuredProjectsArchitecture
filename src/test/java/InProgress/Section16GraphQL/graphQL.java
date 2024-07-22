package InProgress.Section16GraphQL;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class graphQL {
    @Test(dataProvider = "GraphQL")
    public void graphQlTestCases(String locationName, String episodeName) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String graphQL =
                given().header("Content-Type", "application/json")
                        .body("{\"query\":\"mutation ($locationName: String!, $episodeName: String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"South\\\",dimension:\\\"234\\\"})\\n  {\\n    id\\n  }\\n  \\n  \\n  createEpisode(episode:{name:$episodeName, air_date:\\\"22.07.24\\\",episode:\\\"224\\\"})\\n  {\\n    id\\n  }\\n}\",\"variables\":{\"locationName\":\""+locationName+"\",\"episodeName\":\""+episodeName+"\"}}")
                        .when().post("gq/graphql")
                        .then().assertThat().statusCode(200).extract().asString();

        JsonPath graphQLJsonPath = JsonPath.from(graphQL);
        String createLocationId = graphQLJsonPath.getString("data.createLocation.id");
        System.out.println(createLocationId);
        String createEpisodeId = graphQLJsonPath.getString("data.createEpisode.id");
        System.out.println(createEpisodeId);

        Assert.assertNotNull(createLocationId,"createLocationId is null");
        Assert.assertNotNull(createEpisodeId,"createEpisodeId is null");
    }

    @DataProvider(name = "GraphQL")
    public Object[][] setData() {
        return new Object[][]{
                {"Georgia", "Georgia on my mind"},
                {"Lailashi", "Lailashi on my mind"}
        };
    }
}
