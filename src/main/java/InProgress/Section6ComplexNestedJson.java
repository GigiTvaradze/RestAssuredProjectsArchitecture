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
        //Number of courses
        Integer courseSize = js.getInt("courses.size()");
        System.out.println(courseSize);
        //Purchase Amount
        Integer purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        //Title of the first Course
        String firstCoursesTitle = js.getString("courses[0].title");
        System.out.println(firstCoursesTitle);
        //Print All course titles
        for (int i = 0; i < courseSize; i++) {
            String courseTitles = js.get("courses["+i+"].title");
            System.out.println(courseTitles);
        }
        //All course titles and their respective Prices
        for (int i = 0; i < courseSize; i++) {
           String courseTitles = js.get("courses["+i+"].title");
           Integer coursePrices = js.get("courses["+i+"].price");
            System.out.println(courseTitles);
            System.out.println(coursePrices);
        }
        //Number of copies sold by RPA Course
        System.out.println();

        //Verify if Sum of all Course prices matches with purchase Amount
        System.out.println();
    }

    public void tearDown() {
        // Perform teardown tasks here, such as closing connections or releasing resources
        System.out.println("Teardown tasks after each test method");
    }
}