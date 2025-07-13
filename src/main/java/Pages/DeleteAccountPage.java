package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    By Btn_DAccount = By.xpath("//a[contains(text(), \"Delete Account\")]");
    By DeleteAccountValidation = By.xpath("//*[contains(@data-qa, \"account-deleted\")]");
    By Btn_Continue = By.xpath("//*[contains(@data-qa, \"continue-button\")]");

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String performDeleteAccount() {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(Btn_DAccount)).click();
            driver.findElement(DeleteAccountValidation).isDisplayed();
            wait.until(ExpectedConditions.elementToBeClickable(Btn_Continue)).click();

            return "done";
        }
        catch (Exception e) {
            System.out.println("Error during signup:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


