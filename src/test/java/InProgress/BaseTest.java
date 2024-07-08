package InProgress;

import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        // Setup tasks, such as initializing variables, opening connections, etc.
        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }
    @AfterMethod
    public void tearDown() {
        // Perform teardown tasks here, such as closing connections or releasing resources
        System.out.println("Teardown tasks after each test method");
    }
}
