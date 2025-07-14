import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_10 {
    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        homePage = new HomePage(driver);
    }

    @Test
    public void testSubscriptionFeature() {

        homePage.verifyHomePageIsVisible();
        System.out.println("Step 1: Home page is visible successfully");

        homePage.scrollToFooter();
        System.out.println("Step 2: Scrolled to footer section");

        homePage.verifySubscriptionText();
        System.out.println("Step 3: 'SUBSCRIPTION' text is visible");

        homePage.enterEmailAndSubscribe("oshiwijewickrama28@gmail.com");
        System.out.println("Step 4: Email entered and subscribe button clicked");

        homePage.verifySubscriptionSuccessMessage();
        System.out.println("Step 5: Subscription success message displayed");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
