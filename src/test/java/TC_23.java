import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static Pages.TestData.*;

public class TC_23 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SignupPage signupPage;
    AccountCreatedPage accountCreatedPage;
    DeleteAccountPage deleteAccountPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test
    public void TC23(){
        homePage.verifyHomePage();
        loginPage.Signup(name, email);
        signupPage.Signup(gender, password, day, month, year, newsletter, offers, FName, LName, company, address, country, state, city, zipcode, mobile);
        accountCreatedPage.verifyAccountCreated();
        homePage.verifyUsername(name);
        //missing : adding products , proceeding to checkout (steps 8 to 11)
        //to add : verify billing & delivery addresses

        // Add product to cart
        driver.findElement(By.xpath("(//a[contains(text(), 'Add to cart')])[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // click continue shopping
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))).click();

        // Click 'Cart' button
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // Verify cart page is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Shopping Cart')]")));

        // Click Proceed to Checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed To Checkout')]"))).click();

        //just for demo
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 12–13: Verify address details
        String expectedAddressLine = address + "\n" + city + " " + state + " " + zipcode + "\n" + country + "\n" + "0" + mobile;

        String actualDeliveryAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_delivery']"))).getText();
        String actualBillingAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_invoice']"))).getText();

        Assert.assertTrue(actualDeliveryAddress.contains(address), "Delivery address mismatch!");
        Assert.assertTrue(actualBillingAddress.contains(address), "Billing address mismatch!");

        System.out.println("Delivery and Billing address verified successfully.");

        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}