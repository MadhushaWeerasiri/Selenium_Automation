import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_12 {
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
    public void testAddProductsToCart() {
        homePage.verifyHomePageIsVisible();
        System.out.println("Step 1: Home page is visible successfully");

        productPage.clickProductsButton();
        System.out.println("Step 2: Clicked on 'Products' button");

        productPage.addFirstProductToCart();
        System.out.println("Step 3: First product added to cart and continued shopping");

        productPage.addSecondProductToCartAndViewCart();
        System.out.println("Step 4: Second product added to cart and navigated to cart page");

        cartPage.verifyProductsInCart();
        System.out.println("Step 5: Verified both products are added to cart");

        cartPage.verifyPricesQuantitiesAndTotals();
        System.out.println("Step 6: Verified prices, quantities, and total prices are correct");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
