import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_13 {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testProductQuantityInCart() {
        homePage.verifyHomePageIsVisible();
        System.out.println("Step 1: Home page is visible");

        productPage.clickViewProduct();
        System.out.println("Step 2: Clicked 'View Product'");

        productPage.verifyProductDetailPageOpened();
        System.out.println("Step 3: Verified product detail page");

        productPage.setProductQuantity(4);
        System.out.println("Step 4: Set quantity to 4");

        productPage.clickAddToCart();
        System.out.println("Step 5: Added product to cart");

        productPage.clickViewCart();

        cartPage.verifyProductQuantity("4");
        System.out.println("Step 6: Product is displayed in cart page with exact quantity");
    }

    @AfterClass
    public void tearDown()  {
        driver.quit();
    }
}
