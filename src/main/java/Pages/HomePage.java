package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By HomeValidation = By.xpath("//h1[contains(text(), \"Exercise\")]");
    By userValidation = By.xpath("//h1[contains(text(), \"Logged in as\")]");
    By Btn_Logout = By.xpath("//a[contains(text(), \" Logout\")]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void verifyHomePage() {
        ExpectedConditions.visibilityOfElementLocated(HomeValidation);
        Assert.assertTrue(driver.findElement(HomeValidation).isDisplayed(), "Home page heading not displayed");
    }

    public void verifyUsername(String name) {
        ExpectedConditions.visibilityOfElementLocated(By.id("//a[@class='fa fa-user']/following-sibling::text()[contains(., 'Logged in as ')]/following-sibling::b[contains(text(), '" + name + "')]"));
    }

    public void verifyUser() {
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='fa fa-user']/following-sibling::text()[contains(., 'Logged in as ')]/following-sibling::b"));
    }

    public void logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(Btn_Logout)).click();
    }
}