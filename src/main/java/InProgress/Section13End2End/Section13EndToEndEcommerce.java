package InProgress.Section13End2End;

import InProgress.BaseTest;
import InProgress.ReUsableMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

//>>>TEST IS CORRECT NEED REGISTRATION ON SITE FOR REGISTER AN EMAIL, THAT's WHY: "message": "Email is required"
public class Section13EndToEndEcommerce extends BaseTest {

    //End to End Flow:
    //Login API -> Create Product -> Purchase Order on created Product -> Delete order -> Delete Product
    @Test
    public void section13EndToEndEcommerce() {

        loginPayloadPojoClass loginPayload = new loginPayloadPojoClass();
        loginPayload.setUserEmail("GgMornaaaaing@gmail.com");
        loginPayload.setUserPassword("MorningStar@666");

        //LoginApi
        String loginApiRequest = given().
                log()
                .all()
                .body(loginPayload)
                .when()
                .post("api/ecom/auth/login")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200).extract().asString();
        JsonPath js = ReUsableMethods.rawToJson(loginApiRequest);
        String sessionToken = js.get("token");
        String userId = js.get("userId");
        String message = js.get("message");

        Assert.assertEquals(message,"Login Successfully");

        //Create Product
        String createProductApiRequest = given()
                .header("Authorization",sessionToken)
                .formParam("productName","MayHoney")
                .formParam("productAddedBy",userId)
                .formParam("productCategory","fashion")
                .formParam("productSubCategory","shirts")
                .formParam("productPrice","11500")
                .formParam("productDescription","Adidas Originals")
                .formParam("productFor","Unisex")
                .multiPart("productImage",new File("/Users/gtv/Desktop/Momavali.png"))
                .when()
                .post("api/ecom/product/add-product")
                .then()
                .assertThat()
                .statusCode(200).extract().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(createProductApiRequest);
        String productId = js1.get("productId");
        String messageOfAddedProduct = js1.get("message");

        Assert.assertEquals(messageOfAddedProduct,"Product Added Successfully");

        //Create Order
        createOrderPojoClass orderDetails = new createOrderPojoClass();
        orderDetails.setCountry("Georgia");
        orderDetails.setProductOrderedId(productId);

        List<createOrderPojoClass> orderPayloadList = new ArrayList<createOrderPojoClass>();
        orderPayloadList.add(orderDetails);

        mainCreateOrderPojoClass order= new mainCreateOrderPojoClass();
        order.setCreateOrderPojoClassList(orderPayloadList);

        String createOrder = given()
                .header("Authorization",sessionToken)
                .body(order)
                .when()
                .post("api/ecom/order/create-order")
                .then()
                .assertThat()
                .statusCode(201).extract().asString();

        JsonPath js2 = ReUsableMethods.rawToJson(createOrder);
        String createOrderMessage = js2.get("message");
        Assert.assertEquals(createOrderMessage,"Order Placed Successfully");

        //Delete Added Product
        String productDelete = given().relaxedHTTPSValidation()
                .pathParam("productId",productId)
                .header("Authorization",sessionToken)
                .when()
                .delete("api/ecom/product/delete-product/{productId}")
                .then()
                .assertThat()
                .statusCode(200).extract().asString();

        JsonPath js3 = ReUsableMethods.rawToJson(productDelete);
        String deleteProductMessage = js3.get("message");
        Assert.assertEquals(deleteProductMessage,"Product Deleted Successfully");
    }
}