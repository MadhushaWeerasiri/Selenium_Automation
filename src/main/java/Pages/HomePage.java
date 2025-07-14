package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By lbl_homeVisible = By.xpath("//a[contains(text(),' Home')]");
    By footer_section = By.id("footer");
    By lbl_subscription = By.xpath("//h2[contains(text(),'Subscription')]");
    By input_email = By.id("susbscribe_email");
    By btn_arrow = By.id("subscribe");
    By success_message = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");
    By btn_cart = By.xpath("//a[contains(text(),' Cart')]");
    By btn_products = By.xpath("//a[contains(text(),'Products')]");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyHomePageIsVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(lbl_homeVisible));
        Assert.assertTrue(driver.findElement(lbl_homeVisible).isDisplayed(), "Home page is not visible");
    }

    public void scrollToFooter() {
        WebElement footer = driver.findElement(footer_section);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
    }

    public void verifySubscriptionText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lbl_subscription));
        Assert.assertTrue(driver.findElement(lbl_subscription).isDisplayed(), "'SUBSCRIPTION' text not found!");
    }

    public void enterEmailAndSubscribe(String email) {
        driver.findElement(input_email).sendKeys(email);
        driver.findElement(btn_arrow).click();
    }

    public void verifySubscriptionSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(success_message));
        Assert.assertTrue(driver.findElement(success_message).isDisplayed(), "Success message not displayed!");
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_cart));
        driver.findElement(btn_cart).click();
    }

    public void navigateToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_products)).click();
    }
}
