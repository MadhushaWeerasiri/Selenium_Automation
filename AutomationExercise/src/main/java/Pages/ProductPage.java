package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    By btn_products = By.xpath("//a[@href='/products']");

    By firstProductCard = By.xpath("//img[@alt='ecommerce website products']");
    By firstAddToCartBtn = By.xpath("//a[@data-product-id='1']");
    By btn_continueShopping = By.xpath("//button[contains(text(), 'Continue Shopping')]");

    By secondProductCard = By.xpath("(//img[@alt='ecommerce website products'])[2]");
    By secondAddToCartBtn = By.xpath("//a[@data-product-id='2']");

    By btn_viewCart = By.xpath("//a[@href='/view_cart']");

    By viewProductBtn = By.xpath("//a[@href='/product_details/3']");
    By productName = By.xpath("//h2[contains(text(), 'Sleeveless')]");
    By quantityInput = By.xpath("//input[@id='quantity']");
    By btn_addToCart = By.xpath("//button[@type='button' and contains(., 'Add to cart')]");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void clickProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_products)).click();
    }

    public void addFirstProductToCart() {
        actions.moveToElement(driver.findElement(firstProductCard)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_continueShopping)).click();
    }

    public void addSecondProductToCartAndViewCart() {
        actions.moveToElement(driver.findElement(secondProductCard)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(secondAddToCartBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_viewCart)).click();
    }


    public void clickViewProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductBtn)).click();
    }

    public void verifyProductDetailPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        Assert.assertTrue(driver.findElement(productName).isDisplayed(), "Product detail not visible!");
    }

    public void setProductQuantity(int qty) {
        WebElement qtyInput = driver.findElement(quantityInput);
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(qty));
    }

    public void clickAddToCart() {
        driver.findElement(btn_addToCart).click();
        wait.until(ExpectedConditions.elementToBeClickable(btn_viewCart)).click();
    }

    public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_viewCart)).click();
    }

}
