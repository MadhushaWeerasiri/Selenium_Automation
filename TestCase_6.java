import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.time.Duration;

public class TestCase_6 {

    public static void main(String[] args) {

        // 1. Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");

        // 2. Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        try {
            // 3. Navigate to URL
            driver.get("https://www.automationexercise.com");

            // 4. Verify that home page is visible
            if (driver.getTitle().contains("Automation Exercise")) {
                System.out.println("Home page is visible.");
            } else {
                System.out.println("Home page is NOT visible.");
                return;
            }

            // 5. Click on 'Contact Us' button
            driver.findElement(By.xpath("//a[@href='/contact_us']")).click();

            // 6. Verify 'GET IN TOUCH' is visible
            WebElement getInTouchHeader = driver.findElement(By.xpath("//h2[text()='Get In Touch']"));
            if (getInTouchHeader.isDisplayed()) {
                System.out.println("'GET IN TOUCH' is visible.");
            } else {
                System.out.println("'GET IN TOUCH' is NOT visible.");
                return;
            }

            // 7. Enter name, email, subject and message
            driver.findElement(By.name("name")).sendKeys("Test User");
            driver.findElement(By.name("email")).sendKeys("test@example.com");
            driver.findElement(By.name("subject")).sendKeys("Automation Test");
            driver.findElement(By.name("message")).sendKeys("This is a test message.");



            // 9. Click 'Submit' button
            driver.findElement(By.name("submit")).click();

            // 10. Handle alert
            driver.switchTo().alert().accept(); // Click OK button on alert

            // 11. Verify success message is visible
            WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class, 'status') and contains(text(),'Success!')]"));
            if (successMessage.isDisplayed()) {
                System.out.println("Success message is visible.");
            } else {
                System.out.println("Success message is NOT visible.");
            }

            // 12. Click 'Home' button
            driver.findElement(By.xpath("//a[@class='btn btn-success']")).click();

            // 13. Verify that landed to home page
            if (driver.getCurrentUrl().equals("https://www.automationexercise.com/")) {
                System.out.println("Successfully returned to home page.");
            } else {
                System.out.println("Did NOT return to home page.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
