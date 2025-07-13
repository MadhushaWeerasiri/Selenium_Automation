package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By Btn_login = By.xpath("//a[contains(text(), \" Signup / Login\")]");

    By Txt_Name = By.xpath("//*[contains(@data-qa, \"signup-name\")]");
    By Txt_Email = By.xpath("//*[contains(@data-qa, \"signup-email\")]");
    By Btn_Signup = By.xpath("//*[contains(@data-qa, \"signup-button\")]");

    By LoginFormValidation = By.xpath("//h2[contains(text(), \"Login to your account\")]");
    By Txt_LoginEmail = By.xpath("//*[contains(@data-qa, \"login-email\")]");
    By Txt_LoginPassword = By.xpath("//*[contains(@data-qa, \"login-password\")]");
    By Btn_LogIn = By.xpath("//*[contains(@data-qa, \"login-button\")]");

    By LoginFormFailValidation = By.xpath("//p[contains(text(), \"Your email or password is incorrect!\")]");

    By LoginValidation = By.xpath("//h2[contains(text(), \"Login to your account\")]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public void CheckLoginPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginValidation));
    }

    public void Signup(String name, String email) {
        performSignup(name, email);
    }

    private String performSignup(String name, String email) {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(Btn_login)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Name)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Name)).sendKeys(name);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Email)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Txt_Email)).sendKeys(email);
            wait.until(ExpectedConditions.elementToBeClickable(Btn_Signup)).click();

            return "done";
        }
        catch (Exception e) {
            System.out.println("Error during signup:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void Login(String email, String password) {
        performLogin(email, password);
    }

    private String performLogin(String email, String password) {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(Btn_login)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginFormValidation));
            wait.until(ExpectedConditions.elementToBeClickable(Txt_LoginEmail)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Txt_LoginEmail)).sendKeys(email);
            wait.until(ExpectedConditions.elementToBeClickable(Txt_LoginPassword)).click();
            wait.until(ExpectedConditions.elementToBeClickable(Txt_LoginPassword)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(Btn_LogIn)).click();

            return "done";
        }
        catch (Exception e) {
            System.out.println("Error during login:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void checkLoginFail() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginFormFailValidation));
    }
}
