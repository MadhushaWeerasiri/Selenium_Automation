import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_02 {
    WebDriver driver;
    HomePage HomePage;
    LoginPage LoginPage;
    DeleteAccountPage DeleteAccountPage;

    private String email = "adamsmith@gmail.com";
    private String password = "6ADm@124c7a";

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
    public void TC_02() {
        HomePage.verifyHomePage();
        LoginPage.Login(email, password);
        HomePage.verifyUser();
        DeleteAccountPage.performDeleteAccount();
        HomePage.verifyHomePage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
