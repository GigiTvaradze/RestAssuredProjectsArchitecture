package InProgress;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Section6ComplexNestedJson extends BaseTest{

    @Test
    public void hereWeGo(){
        //Array is collection of different elements
        //Mock response payload.CoursePrice()
        //JsonPath js = new JsonPath(payload.CoursePrice());
        JsonPath js = ReUsableMethods.rawToJson(payload.CoursePrice());

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
        //As a Dynamic Response we need to scan with Title and not with index. Dynamically Scanning:
        //Integer courseSoldByRPA = js.getInt("courses[2].copies");
        for (int i = 0; i < courseSize; i++) {
            String courseTitles = js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA")){
                Integer courseSoldByRPA = js.get("courses["+i+"].copies");
                System.out.println(courseSoldByRPA);
                break;
            }
        }
    }

    @Test(description = "Verify if Sum of all Course prices matches with purchase Amount")
    public void sumOfCourses(){
        int sum = 0;
        JsonPath js = ReUsableMethods.rawToJson(payload.CoursePrice());
        int courseSize = js.getInt("courses.size()");
        for (int i = 0; i < courseSize; i++) {
          int price =  js.get("courses["+i+"].price");
          int copies = js.get("courses["+i+"].copies");
          int amount = price * copies ;
            System.out.println(amount);
            sum += amount;
        }
        System.out.println(sum);
        //wayNo1
        if(sum == js.getInt("dashboard.purchaseAmount")){
            System.out.println("Course price matches with purchase Amount");
        }
        //WayNo2
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);
    }
}