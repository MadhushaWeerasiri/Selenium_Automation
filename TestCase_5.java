import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase_5 {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 1. Navigate to URL
            driver.get("https://www.automationexercise.com");

            // 2. Verify that home page is visible successfully
            String homeTitle = driver.getTitle();
            if (homeTitle.contains("Automation Exercise")) {
                System.out.println("Home page is visible.");
            } else {
                System.out.println("Home page is NOT visible.");
            }

            // 3. Click on 'Signup / Login' button
            WebElement signupLoginBtn = driver.findElement(By.xpath("//a[@href='/login']"));
            signupLoginBtn.click();

            // 4. Verify 'New User Signup!' is visible
            WebElement newUserSignup = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
            if (newUserSignup.isDisplayed()) {
                System.out.println("'New User Signup!' is visible.");
            } else {
                System.out.println("'New User Signup!' is NOT visible.");
            }

            // 5. Enter name and already registered email address
            driver.findElement(By.name("name")).sendKeys("Test User");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("test@example.com");

            // 6. Click 'Signup' button
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

            // 7. Verify error 'Email Address already exist!' is visible
            WebElement errorMsg = driver.findElement(By.xpath("//p[text()='Email Address already exist!']"));
            if (errorMsg.isDisplayed()) {
                System.out.println("Error message is displayed: Email Address already exist!");
            } else {
                System.out.println("Error message NOT displayed.");
            }

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}