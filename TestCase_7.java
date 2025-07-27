import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase_7 {

    public static void main(String[] args) {

        // 1. Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");

        // 2. Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 3. Navigate to URL
            driver.get("https://www.automationexercise.com");

            // 4. Verify that home page is visible successfully
            String title = driver.getTitle();
            if (title.contains("Automation Exercise")) {
                System.out.println("Home page is visible.");
            } else {
                System.out.println("Home page is NOT visible.");
                return;
            }

            // 5. Click on 'Test Cases' button
            driver.findElement(By.xpath("//a[@href='/test_cases']")).click();

            // 6. Verify user is navigated to test cases page successfully
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/test_cases")) {
                System.out.println("Navigated to Test Cases page successfully.");
            } else {
                System.out.println("Did NOT navigate to Test Cases page.");
            }

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}