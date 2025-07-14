package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By footer_section = By.xpath("//*[@id=\"footer\"]");
    By lbl_subscription = By.xpath("//h2[contains(text(),'Subscription')]");
    By input_email = By.id("susbscribe_email");
    By btn_arrow = By.id("subscribe");
    By success_message = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    By firstProductName = By.xpath("//a[@href='/product_details/1']");
    By firstProductPrice = By.xpath("//td[@class='cart_price']/p[text()='Rs. 500']");
    By firstProductQty = By.xpath("//td[@class='cart_quantity']/button[text()='1']");
    By firstProductTotal = By.xpath("//p[@class='cart_total_price' and text()='Rs. 500']");

    By secondProductName = By.xpath("//a[@href='/product_details/2']");
    By secondProductPrice = By.xpath("//td[@class='cart_price']/p[text()='Rs. 400']");
    By secondProductQty = By.xpath("//td[@class='cart_quantity']/button[text()='1']");
    By secondProductTotal = By.xpath("//td[@class='cart_total']/p[@class='cart_total_price' and text()='Rs. 400']");

    By productQty = By.xpath("//td[@class='cart_quantity']//button");


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void verifyProductsInCart() {
        Assert.assertTrue(driver.findElement(firstProductName).isDisplayed(), "First product not in cart");
        Assert.assertTrue(driver.findElement(secondProductName).isDisplayed(), "Second product not in cart");
    }

    public void verifyPricesQuantitiesAndTotals() {
        String price1 = driver.findElement(firstProductPrice).getText();
        String qty1 = driver.findElement(firstProductQty).getText();
        String total1 = driver.findElement(firstProductTotal).getText();

        String price2 = driver.findElement(secondProductPrice).getText();
        String qty2 = driver.findElement(secondProductQty).getText();
        String total2 = driver.findElement(secondProductTotal).getText();

        System.out.println("Product 1 => Price: " + price1 + ", Qty: " + qty1 + ", Total: " + total1);
        System.out.println("Product 2 => Price: " + price2 + ", Qty: " + qty2 + ", Total: " + total2);

        Assert.assertNotNull(price1);
        Assert.assertNotNull(price2);
        Assert.assertEquals(qty1, "1");
        Assert.assertEquals(qty2, "1");
    }

    public void verifyProductQuantity(String expectedQty) {
        String actualQty = driver.findElement(productQty).getText().trim();
        System.out.println("Quantity in cart: " + actualQty);
        Assert.assertEquals(actualQty, expectedQty, "Product quantity in cart is incorrect");
    }
}
