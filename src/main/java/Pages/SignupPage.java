package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;

    By RBtn_Mr = By.id("id_gender1");
    By RBtn_Mrs = By.id("id_gender2");
    By Txt_Password = By.xpath("//*[contains(@data-qa, \"password\")]");
    By Slt_Day = By.xpath("//*[contains(@data-qa, \"days\")]");
    By Slt_Month = By.xpath("//*[contains(@data-qa, \"months\")]");
    By Slt_Year = By.xpath("//*[contains(@data-qa, \"years\")]");
    By Chb_Newsletter = By.id("newsletter");
    By Chb_Offers = By.id("optin");
    By Txt_FName = By.xpath("//*[contains(@data-qa, \"first_name\")]");
    By Txt_LName = By.xpath("//*[contains(@data-qa, \"last_name\")]");
    By Txt_Company = By.xpath("//*[contains(@data-qa, \"company\")]");
    By Txt_Address = By.xpath("//*[contains(@data-qa, \"address\")]");
    By Slt_Country = By.xpath("//*[contains(@data-qa, \"country\")]");
    By Txt_State = By.xpath("//*[contains(@data-qa, \"state\")]");
    By Txt_City = By.xpath("//*[contains(@data-qa, \"city\")]");
    By Txt_Zip = By.xpath("//*[contains(@data-qa, \"zipcode\")]");
    By Txt_MobNum = By.xpath("//*[contains(@data-qa, \"mobile_number\")]");
    By Btn_CreateAcc = By.xpath("//*[contains(@data-qa, \"create-account\")]");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public void Signup(int gender, String password, int day, String month, int year, boolean newsletter, boolean offers, String FName, String LName, String company, String address, String country, String state, String city, int zipcode, int mobile) {
        performSignup(gender, password, day, month, year, newsletter, offers, FName, LName, company, address, country, state, city, zipcode, mobile);
    }

    private String performSignup(int gender, String password, int day, String month, int year, boolean newsletter, boolean offers, String FName, String LName, String company, String address, String country, String state, String city, int zipcode, int mobile) {
        try {
            // Select gender
            if (gender == 1) {
                wait.until(ExpectedConditions.elementToBeClickable(RBtn_Mr)).click();
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(RBtn_Mrs)).click();
            }

            // Enter password
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Password)).sendKeys(password);

            // Select Day, Month, and Year
            Select daySelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(Slt_Day)));
            daySelect.selectByValue(String.valueOf(day)); // Assuming day is an integer value

            Select monthSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(Slt_Month)));
            monthSelect.selectByVisibleText(month); // Assuming month is a string like "July"

            Select yearSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(Slt_Year)));
            yearSelect.selectByValue(String.valueOf(year)); // Assuming year is an integer value

            // Check Newsletter and Offers checkboxes
            if (newsletter) {
                wait.until(ExpectedConditions.elementToBeClickable(Chb_Newsletter)).click();
            }
            if (offers) {
                wait.until(ExpectedConditions.elementToBeClickable(Chb_Offers)).click();
            }

            // Enter personal details
            wait.until(ExpectedConditions.elementToBeClickable(Txt_FName)).sendKeys(FName);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_LName)).sendKeys(LName);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Company)).sendKeys(company);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Address)).sendKeys(address);

            // Select Country
            Select countrySelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(Slt_Country)));
            countrySelect.selectByVisibleText(country);

            // Enter State, City, Zipcode, and Mobile
            wait.until(ExpectedConditions.elementToBeClickable(Txt_State)).sendKeys(state);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_City)).sendKeys(city);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Zip)).sendKeys(String.valueOf(zipcode));
            wait.until(ExpectedConditions.elementToBeClickable(Txt_MobNum)).sendKeys(String.valueOf(mobile));

            // Click Create Account button
            wait.until(ExpectedConditions.elementToBeClickable(Btn_CreateAcc)).click();

            // Return validation message or success indicator
            return "done";

        } catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}