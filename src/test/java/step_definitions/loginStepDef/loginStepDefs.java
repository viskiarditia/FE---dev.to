package step_definitions.loginStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import step_definitions.Hooks;
import java.time.Duration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class loginStepDefs {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public loginStepDefs() {
        super();
        this.webDriver = Hooks.webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        new Actions(webDriver);
        new WebDriverWait(webDriver, Duration.ofSeconds(20));
    }

    // Config Loader
    public static class ConfigLoader {
        private final Properties properties;

        public ConfigLoader(String configFilePath) {
            properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream(configFilePath)) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // nilai file konfigurasi
        public String getProperty(String key) {
            return properties.getProperty(key);
        }
    }

    @Given("Halaman DEV Community terbuka di browser")
    public void halamanDEVCommunityTerbukaDiBrowser() {
        WebElement LogoWebSite = webDriver.findElement(By.cssSelector(".registration__logo"));
        LogoWebSite.isDisplayed();
        Assert.assertTrue(LogoWebSite.isDisplayed());
    }

    @When("Klik tombol Continue with {string}")
    public void klikTombolContinueWith(String Platform) throws InterruptedException {
        String xpath = getPlatformXPath(Platform);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        button.click();
        Thread.sleep(1000);
    }

    private String getPlatformXPath(String platform) {
        switch (platform) {
            case "Apple":
                return "//span[normalize-space()='Continue with Apple']";
            case "Facebook":
                return "//span[normalize-space()='Continue with Facebook']";
            case "Forem":
                return "//span[normalize-space()='Continue with Forem']";
            case "GitHub":
                return "//span[normalize-space()='Continue with GitHub']";
            case "Google":
                return "//span[normalize-space()='Continue with Google']";
            case "Twitter (X)":
                return "//span[normalize-space()='Continue with Twitter (X)']";
            default:
                throw new IllegalArgumentException("Platform tidak dikenali: " + platform);
        }
    }


    @Then("Halaman Masuk ke dengan {string} dapat ditampilkan dengan form yang sesuai")
    public void halamanMasukKeDenganDapatDitampilkanDenganFormYangSesuai(String expectedText) {
        String pageText = webDriver.getPageSource();
        Assert.assertTrue("Expected text: " + expectedText + " not found in page text.",
                pageText != null && pageText.contains(expectedText)
        );
    }


    @When("ketikan email invalid data, pada field email")
    public void ketikanEmailInvalidDataPadaFieldEmail() {
        ConfigLoader configLoader = new ConfigLoader("src/test/resources/features/config.properties");
        String invalidEmail= configLoader.getProperty("user.email");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.sendKeys(invalidEmail);
    }

    @Then("ketikan password invalid data, pada field password")
    public void ketikanPasswordInvalidDataPadaFieldPassword() {
        ConfigLoader configLoader = new ConfigLoader("src/test/resources/features/config.properties");
        String invalidPassword = configLoader.getProperty("user.password");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']")));
        passwordField.sendKeys(invalidPassword);
    }

    @And("tidak mencentang Remember me?")
    public void tidakMencentangRememberMe()  {
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user_remember_me")));
        if (rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

        @Then("Klik Log in")
        public void klikLogIn () throws InterruptedException {
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit']")));
            loginButton.click();
            Thread.sleep(2000);
        }

}


