package InProgress.Section11;

import InProgress.BaseTest;
import InProgress.payload;
import org.testng.annotations.Test;


import InProgress.BaseTest;
import InProgress.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Section11SerializationPojoClasses extends BaseTest {
    //SERIALIZATION >>> CONVERT JAVA OBJECT TO JSON

    @Test
    public void serializationPojoWithClasses() {

        //for serialization need to create object of the pojo class

        addPlacePojoClass addPlace = new addPlacePojoClass();

        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress("123 Main St");
        addPlace.setWebsite("http://www.google.com");
        addPlace.setLanguage("French-IN");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);


        childLocationPojoClass locationPojoClass = new childLocationPojoClass();
        locationPojoClass.setLat(-123456.987);
        locationPojoClass.setLng(123456.654);
        addPlace.setLocation(locationPojoClass);

        String Response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                //serialization of payload.AddPlace().JSON.
                //.body(payload.AddPlace())
                //object to JSON
                .body(addPlace) //just give object of POJO class and it automatically converts Object into JSON
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

        //JsonPath js = new JsonPath(Response); //for parsing Json
        JsonPath js = ReUsableMethods.rawToJson(Response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);
        Assert.assertNotNull(placeId);
    }
}
