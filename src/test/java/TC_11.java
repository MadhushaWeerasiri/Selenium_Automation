import Pages.CartPage;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TC_11 {
    WebDriver driver;
    HomePage homePage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testSubscriptionInCartPage() {

        // Verify that home page is visible successfully
        homePage.verifyHomePageIsVisible();
        System.out.println("Step 1: Home page is visible successfully");

        // Click 'Cart' button
        homePage.navigateToCart();
        System.out.println("Step 2: Navigated to Cart page");

        // Scroll down to footer
        cartPage.scrollToFooter();
        System.out.println("Step 3: Scrolled to footer");

        // Verify text 'SUBSCRIPTION'
        cartPage.verifySubscriptionText();
        System.out.println("Step 4: 'SUBSCRIPTION' text is visible");

        // Enter email address in input and click arrow button
        cartPage.enterEmailAndSubscribe("oshiwijewickrama28@gmail.com");
        System.out.println("Step 5: Email entered and subscribe button clicked");

        // Verify success message 'You have been successfully subscribed!' is visible
        cartPage.verifySubscriptionSuccessMessage();
        System.out.println("Step 6: Subscription success message displayed");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
