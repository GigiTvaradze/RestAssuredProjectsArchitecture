package InProgress;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class section7DynamicJsonPayload{

    @Test(description = "Sending parameters to payload from test")
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166/";

        String Response = given().header("content-type", "application/json")
                .body(payload.addBook("2271231233","bcd12321322123"))
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200).body("ID",notNullValue()).extract().body().asString();

        JsonPath js = ReUsableMethods.rawToJson(Response);
        String msg = js.getString("Msg");
        String id = js.getString("ID");
        System.out.println(msg);
        System.out.println(id);

        Assert.assertEquals(msg,"successfully added");
    }

    @Test(description = "DataProvider in TestNG", dataProvider = "addBookWithDataProvider")
    public void addBookWithDataProvider(String aisle, String isbn){
        RestAssured.baseURI = "http://216.10.245.166/";

        String Response = given().header("content-type", "application/json")
                .body(payload.addBook(aisle,isbn))
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200).body("ID",notNullValue()).extract().body().asString();

        JsonPath js = ReUsableMethods.rawToJson(Response);
        String msg = js.getString("Msg");
        String id = js.getString("ID");
        System.out.println(msg);
        System.out.println(id);

        Assert.assertEquals(msg,"successfully added");
    }

    @DataProvider(name = "addBookWithDataProvider")
    public Object[][] getData(){
        //array=collection of elements
        //multidimensional array = collection of arrays
        return new Object[][]{
                {"227123123322","bcd1232132212322"},
                {"2271231233444","bcd12321322123444"},
                {"2271231233321","bcd12321322123321"}
        };
    }


    //first need to convert content of the file to String
    //process is: convert file into Byte > Byte data to String
    @Test()
    public void payLoadFromExternalFile() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String Response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                //.body(new String(Files.readAllBytes(Paths.get("/Users/gtv/Desktop/addPlace.json"))))
                .body(ReUsableMethods.GenerateStringFromResource("/Users/gtv/Desktop/addPlace.json"))
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println(Response);
    }
}
