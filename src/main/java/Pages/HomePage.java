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
