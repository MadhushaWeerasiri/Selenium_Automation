import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TC_01 {
    WebDriver driver;

    LoginPage LoginPage;
    SignupPage SignupPage;
    HomePage HomePage;
    AccountCreatedPage AccountCreatedPage;
    DeleteAccountPage DeleteAccountPage;

    private String name = "adam";
    private String email = "adamsmith@gmail.com";
    private int gender = 1;
    private String password = "6ADm@124c7a";
    private int day = 5;
    private String month = "June";
    private int year = 2001;
    private boolean newsletter = true;
    private boolean offers = true;
    private String FName = "adam";
    private String LName = "smith";
    private String company = "11lab";
    private String address = "delhi, india";
    private String country = "India";
    private String state = "Delhi";
    private String city = "Delhi";
    private int zipcode = 44000;
    private int mobile = 0710000000;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/madhusha/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        LoginPage = new LoginPage(driver);
        SignupPage = new SignupPage(driver);
        HomePage = new HomePage(driver);
        AccountCreatedPage = new AccountCreatedPage(driver);
        DeleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test
    public void TC_01() {
        HomePage.verifyHomePage();
        LoginPage.Signup(name, email);
        SignupPage.Signup(gender, password, day, month, year, newsletter, offers, FName, LName, company, address, country, state, city, zipcode, mobile);
        AccountCreatedPage.verifyAccountCreated();
        HomePage.verifyUsername(name);
        DeleteAccountPage.performDeleteAccount();
        HomePage.verifyHomePage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
