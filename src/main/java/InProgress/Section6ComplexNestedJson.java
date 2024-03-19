package InProgress;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Section6ComplexNestedJson {

    @BeforeMethod
    public void setUp() {
        // Setup tasks, such as initializing variables, opening connections, etc.
        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }

    @Test
    public void hereWeGo(){
        //Array is collection of different elements
        JsonPath js = new JsonPath(payload.CoursePrice());
        js.getString("dashboard.purchaseAmount");
        System.out.println(js.getString("dashboard.purchaseAmount"));

    }

    public void tearDown() {
        // Perform teardown tasks here, such as closing connections or releasing resources
        System.out.println("Teardown tasks after each test method");
    }
}