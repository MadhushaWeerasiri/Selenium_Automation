import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_26 {
    WebDriver driver;
    HomePage homePage;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        homePage = new HomePage(driver);

    }

    @Test
    public void TC26() {
        homePage.verifyHomePage();
        homePage.scrollDown();
        homePage.verifySubscription();
        homePage.scrollUp();
        homePage.verifyBannerText();
    }


    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
