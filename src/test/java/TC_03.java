import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_03 {
    WebDriver driver;
    HomePage HomePage;
    LoginPage LoginPage;
    DeleteAccountPage DeleteAccountPage;

    private String email = "dilshan@gmail.com";
    private String password = "55I808BkqP";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/madhusha/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        LoginPage = new LoginPage(driver);
        HomePage = new HomePage(driver);
        DeleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test
    public void TC_03() {
        HomePage.verifyHomePage();
        LoginPage.Login(email, password);
        LoginPage.checkLoginFail();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
