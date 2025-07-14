package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
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
        this.actions = new Actions(driver);
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

    Actions actions;
    By SubscribeElement = By.xpath("//h2[contains(text(), 'Subscription')]");
    By RecommendedItems = By.xpath("//div[contains(@class, 'recommended_items')]");

    //Scrolls to the bottom until the "Subscription" section is visible
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");

        // Optional: pause to visually observe the scroll
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Verifies that the subscription header is visible
    public void verifySubscription() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SubscribeElement));
        System.out.println("'SUBSCRIPTION' section is visible.");
    }

    // Scroll to the very top of the page without using button
    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");

        // Optional: wait to observe the scroll visually
        try {
            Thread.sleep(1000); // For demo only
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Verify the automation banner text is visible
    public void verifyBannerText() {
        By BannerText = By.xpath("//*[contains(text(),'Full-Fledged practice website for Automation Engineers')]");
        WebElement bannerText = wait.until(ExpectedConditions.visibilityOfElementLocated(BannerText));
        System.out.println(" Banner text is visible: " + bannerText.getText());
    }

    // to scroll up using the button
    public void scrollUpWithButton(){
        WebElement scrollUpButton = driver.findElement(By.xpath ("//*[@id='scrollUp']"));
        scrollUpButton.click();

        try {
            Thread.sleep(1000); // For demo only
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyRecommendItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(RecommendedItems));
        System.out.println("'RECOMMENDED ITEMS' section is visible.");
    }
}