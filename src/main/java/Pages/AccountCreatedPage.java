package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountCreatedPage {
    WebDriver driver;
    WebDriverWait wait;

    By Btn_Continue = By.xpath("//*[contains(@data-qa, \"continue-button\")]");
    By AccountCreatedValidation = By.xpath("//*[contains(@data-qa, \"account-created\")]");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String verifyAccountCreated() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(AccountCreatedValidation));
            if (driver.findElement(AccountCreatedValidation).isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(Btn_Continue)).click();
                return "Account creation verified and continued";
            } else {
                return "Account creation validation not displayed";
            }
        } catch (Exception e) {
            System.out.println("Error during account creation verification: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}