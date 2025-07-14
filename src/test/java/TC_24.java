import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static Pages.TestData.*;

public class TC_24 {
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
    public void TC24() {
        homePage.verifyHomePage();

        //missing : adding products , proceeding to checkout (steps 4 to 7)

        // Add product to cart
        driver.findElement(By.xpath("(//a[contains(text(), 'Add to cart')])[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Continue Shopping
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))).click();

        // Handle potential modal blocking further actions
        try {
            WebElement closeModal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-dismiss='modal']")));
            closeModal.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkoutModal")));
        } catch (Exception e) {
            System.out.println("Modal not present or already closed.");
        }

        // Click Signup/Login
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        // Perform signup
        loginPage.Signup(name, email);
        signupPage.Signup(gender, password, day, month, year, newsletter, offers, FName, LName, company, address, country, state, city, zipcode, mobile);
        accountCreatedPage.verifyAccountCreated();
        homePage.verifyUsername(name);

        // Go to Cart
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // Proceed to Checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed To Checkout')]"))).click();

        // Verify address
        String expectedAddressLine = address + "\n" + city + " " + state + " " + zipcode + "\n" + country + "\n" + "0" + mobile;
        String actualDeliveryAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_delivery']"))).getText();
        String actualBillingAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_invoice']"))).getText();
        Assert.assertTrue(actualDeliveryAddress.contains(address), "Delivery address mismatch!");
        Assert.assertTrue(actualBillingAddress.contains(address), "Billing address mismatch!");

        // Enter comment
        driver.findElement(By.name("message")).sendKeys("Test order from automation script");

        // Place Order
        driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();

        // Enter card details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card"))).sendKeys("Stan Perera");
        driver.findElement(By.name("card_number")).sendKeys("4111111111111111");
        driver.findElement(By.name("cvc")).sendKeys("123");
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        driver.findElement(By.name("expiry_year")).sendKeys("2026");

        // Click Pay
        driver.findElement(By.id("submit")).click();

        // Verify order success
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Your order has been confirmed')]")));
        Assert.assertTrue(successMsg.getText().contains("Your order has been confirmed"));

        // to add : download invoice and continue
        // Download Invoice
        WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Download Invoice')]")));
        downloadBtn.click();
        try {
            Thread.sleep(2000); // Wait for download to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Continue after invoice download
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='continue-button']"))).click();

        // Delete Account
        deleteAccountPage.performDeleteAccount();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}