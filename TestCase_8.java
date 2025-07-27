import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class TestCase_8 {

    public static void main(String[] args) {

        // 1. Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");

        // 2. Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // 3. Navigate to URL
            driver.get("https://www.automationexercise.com");

            // 4. Verify home page is visible successfully
            String title = driver.getTitle();
            if (title.contains("Automation Exercise")) {
                System.out.println("Home page is visible.");
            } else {
                System.out.println("Home page is NOT visible.");
                return;
            }

            // 5. Click on 'Products' button
            driver.findElement(By.xpath("//a[@href='/products']")).click();

            // 6. Verify navigation to 'ALL PRODUCTS' page
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/products")) {
                System.out.println("Navigated to ALL PRODUCTS page successfully.");
            } else {
                System.out.println("Failed to navigate to ALL PRODUCTS page.");
                return;
            }

            // 7. Verify that product list is visible
            List<WebElement> products = driver.findElements(By.className("product-image-wrapper"));
            if (!products.isEmpty()) {
                System.out.println("Product list is visible.");
            } else {
                System.out.println("Product list is NOT visible.");
                return;
            }

            // 8. Click on 'View Product' of the first product
            WebElement firstViewProduct = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
            firstViewProduct.click();

            // 9. Verify that product detail page is displayed
            WebElement productName = driver.findElement(By.xpath("//div[@class='product-information']/h2"));
            WebElement category = driver.findElement(By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]"));
            WebElement price = driver.findElement(By.xpath("//div[@class='product-information']/span/span[contains(text(),'Rs.')]"));
            WebElement availability = driver.findElement(By.xpath("//div[@class='product-information']/p[b[contains(text(),'Availability')]]"));
            WebElement condition = driver.findElement(By.xpath("//div[@class='product-information']/p[b[contains(text(),'Condition')]]"));
            WebElement brand = driver.findElement(By.xpath("//div[@class='product-information']/p[b[contains(text(),'Brand')]]"));

            if (productName.isDisplayed() && category.isDisplayed() && price.isDisplayed()
                    && availability.isDisplayed() && condition.isDisplayed() && brand.isDisplayed()) {
                System.out.println("Product Name visible" + productName.isDisplayed());
                System.out.println("Category visible: " + category.isDisplayed());
                System.out.println("Price visible: " + price.isDisplayed());
                System.out.println("Availability visible:" + availability.isDisplayed());
                System.out.println("Condition visible:" + condition.isDisplayed());;
                System.out.println("Brand visible:" + brand.isDisplayed());;
            } else {
                System.out.println("One or more product details are NOT visible.");
            }

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            // Close browser
            driver.quit();
        }
    }
}