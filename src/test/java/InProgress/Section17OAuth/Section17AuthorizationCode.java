package InProgress.Section17OAuth;


//This section talks about Grant Type called "Authorization Code" .
//This Grant type involve browser intervention for complete.
//That means you may need basic selenium knowledge along with Rest Assured to complete end to end automation.
//If you are aware of basic selenium, then you can continue to learn this section to understand how Google third party OAuth authentication happens.

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Section17AuthorizationCode {
    //END2END Test:
    // GET Code. To get Code needs Query Params which will retrieve from browser link. operation should perform on browser  >
    //GET Access Token >
    //GET Courses.

    //HELP OF SELENIUM
    //Hit URL > opens Sign-in page on Google
    //Log in on Page
    //In the response on the URL, will get the code. grab and parse the response.

     @Test
        public void authorizationCodeTest() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/gtv/Downloads/chromedriver-mac-arm64/chromedriver");
    WebDriver driver = new ChromeDriver();
    driver.get("https://accounts.google.com/v3/signin/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&ddm=0&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hANG-BSE4xmvx0iIWYQH_L2jcgVYCN680Vd_Koxk7kOMD3q8IsFWMpa2okLIXJizTFriui6q_OKQO-4EsDM0pY2sZum5Vz4brxgyTHo82cWjGlIR65Ca76MVU4_HsfiS0syf7_LKSO5AkFYpxHWixSMcyyb6JqbeTbFZkNIM8xafJ1vszqpl5SDlRUY0K6GSCv1ki0Zv7sTxJBf_fE1KobB5Hct8PrJ417u6FbXlabXkBMkB0UWUJtiZSf0ZZlq4oonkLhr6n6QgYBLbKsYQl0PiTBloVVRZurF9jrHsrSYYGrOu5a-g2x_rxtDebKXapqrArrGa08iEu5e1MFHe-D8DNpaa6nKle2bwh_YM527dE7RIGR11V5gQyNpo0ze0ClOLmudIGjsskAjN6nJW9MUCwCY5ZIR01gX4XzhrD3ZhG6OXoY0TojyLLcR4Pur_ij7I9u3XgMF7UMiJlmimGstsCOVrhA%26flowName%3DGeneralOAuthFlow%26as%3DS875681248%253A1721723073157756%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23");
    driver.findElement(By.id("identifierId")).sendKeys("");
    driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("input[Type='password']")).sendKeys("password");
    driver.findElement(By.cssSelector("input[Type='password']")).sendKeys(Keys.ENTER);
    Thread.sleep(5000);

         String url = driver.getCurrentUrl();
         String partialcode=url.split("code=")[1];
         String code=partialcode.split("&scope")[0];
         System.out.println(code);

         String getAccessToken =
                 given()
                         .urlEncodingEnabled(false)
                         .queryParams("code",code)
                         .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                         .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                         .queryParams("grant_type", "authorization_code")
                         .queryParams("state", "verifyfjdss")
                         .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                         // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                         .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                         .when().log().all()
                         .post("https://www.googleapis.com/oauth2/v4/token").asString();
         JsonPath jsonPath = new JsonPath(getAccessToken);
         String accessToken = jsonPath.getString("access_token");
         System.out.println(accessToken);

         String r2=    given().contentType("application/json").
                 queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                 .when()
                 .get("https://rahulshettyacademy.com/getCourse.php")
                 .asString();

         System.out.println(r2);
     }
}
