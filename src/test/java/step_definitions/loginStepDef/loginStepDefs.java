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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class loginStepDefs {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    private ConfigLoader configLoader;

    private void loadConfig() {
        if (configLoader == null) {
            configLoader = new ConfigLoader("src/test/resources/features/config.properties");
        }
    }
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
    public void halamanDEVCommunityTerbukaDiBrowser() throws InterruptedException {
        WebElement LogoWebSite = webDriver.findElement(By.cssSelector(".registration__logo"));
        LogoWebSite.isDisplayed();
        Assert.assertTrue(LogoWebSite.isDisplayed());
        Thread.sleep(1000);
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
        loadConfig();
        String invalidEmail = configLoader.getProperty("user.email");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.sendKeys(invalidEmail);
    }

    @Then("ketikan password invalid data, pada field password")
    public void ketikanPasswordInvalidDataPadaFieldPassword() {
        loadConfig();
        String invalidPassword = configLoader.getProperty("user.password");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']")));
        passwordField.sendKeys(invalidPassword);
    }

    @And("tidak mencentang Remember me?")
    public void tidakMencentangRememberMe() {
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user_remember_me")));
        if (rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    @Then("Klik Log in")
    public void klikLogIn() throws InterruptedException {
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit']")));
        loginButton.click();
        Thread.sleep(2000);
    }

    @When("Kosongkan pada field Email")
    public void kosongkanPadaFieldEmail() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.sendKeys(" ");
    }

    @Then("Kosongkan pada field password")
    public void kosongkanPadaFieldPassword() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']")));
        passwordField.sendKeys(" ");
    }

    @When("ketikan email tidak valid data, pada field email")
    public void ketikanEmailTidakValidDataPadaFieldEmail() {
        loadConfig();
        String invalidEmail2 = configLoader.getProperty("user.email2");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.sendKeys(invalidEmail2);
    }

    @Then("ketikan password invalid data kurang dari enam karakter, pada field password")
    public void ketikanPasswordInvalidDataKurangDariKarakterPadaFieldPassword() {
        loadConfig();
        String invalidPassword2 = configLoader.getProperty("user.password2");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']")));
        passwordField.sendKeys(invalidPassword2);
    }

    private static final Logger logger = Logger.getLogger(loginStepDefs.class.getName());

    @When("ketikan email invalid {string}, pada field email")
    public void ketikanEmailInvalidPadaFieldEmail(String testCase) throws InterruptedException {
        loadConfig();
        String propertyKey = convertToPropertyKey(testCase);
        String invalidEmail = configLoader.getProperty(propertyKey);
        if (invalidEmail == null || invalidEmail.isEmpty()) {
            throw new IllegalArgumentException("Invalid email not found in config.properties for key: " + propertyKey);
        }

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.clear();
        Thread.sleep(1000);
        emailField.sendKeys(invalidEmail);
        logger.info("Typed invalid email: " + invalidEmail + " for test case: " + testCase);
    }

    private String convertToPropertyKey(String testCase) {
        // Mapping of test case descriptions to property keys
        Map<String, String> keyMapping = new HashMap<>();
        keyMapping.put("Masukkan email valid tetapi menggunakan huruf kapital",
                "Masukkan.email.valid.tetapi.menggunakan.huruf.kapital");
        keyMapping.put("Verifikasi Masukkan email valid dengan format",
                "Verifikasi.Masukkan.email.valid.dengan.format");
        keyMapping.put("Verifikasi Masukkan Format Email Tidak Valid",
                "Verifikasi.Masukkan.Format.Email.Tidak.Valid");
        keyMapping.put("Field Email tidak valid melebihi 25 karakter",
                "Field.Email.tidak.valid.melebihi.25.karakter");
        keyMapping.put("Field Email Kosong", "Field.Email.Kosong");
        keyMapping.put("Masukkan email dengan format valid tetapi tanpa nama pengguna",
                "Masukkan.email.dengan.format.valid.tetapi.tanpa.nama.pengguna");
        keyMapping.put("Masukkan email valid tetapi dengan spasi di awal/akhir",
                "Masukkan.email.valid.tetapi.dengan.spasi.di.awal/akhir");
        keyMapping.put("Verifikasi field email dengan ~ (tilde)",
                "Verifikasi.field.email.dengan.~.(tilde)");
        keyMapping.put("(exclamation mark)", "exclamation.mark");
        keyMapping.put("(hash)", "hash");
        keyMapping.put("(dollar)", "dollar");
        keyMapping.put("(percent)", "percent");
        keyMapping.put("(caret)", "caret");
        keyMapping.put("(ampersand)", "ampersand");
        keyMapping.put("(asterisk)", "asterisk");
        keyMapping.put("(angle brackets)", "angle.brackets");
        keyMapping.put("(parentheses)", "parentheses");
        keyMapping.put("(underscore)", "underscore");
        keyMapping.put("(plus)", "plus");
        keyMapping.put("(equals)", "equals");
        keyMapping.put("(backslash)", "backslash");
        keyMapping.put("(vertical bar)", "vertical.bar");
        keyMapping.put("angka", "angka");

        // Return the corresponding property key or throw an exception if not found
        String propertyKey = keyMapping.get(testCase);
        if (propertyKey == null) {
            throw new IllegalArgumentException("Test case not found in mapping: " + testCase);
        }
        return propertyKey;
    }

    @Then("user mendapatkan {string} seusai dengan test case")
    public void userMendapatkanSeusaiDenganTestCase(String expectedResult) {
        WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        try {
            // Wait for the pop-up or error message to appear
            WebElement popUpElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'error-popup') or contains(@class, 'error-message')]")));

            // Get the actual error message
            String actualResult = popUpElement.getText().trim();
            logger.info("Expected result: " + expectedResult);
            logger.info("Actual result: " + actualResult);

            // Validate the actual result against the expected result
            assertEquals("The pop-up message does not match the expected result", expectedResult, actualResult);
        } catch (Exception e) {
            // Fail the test if the pop-up does not appear within 3 seconds
            logger.severe("Pop-up or error message did not appear within 3 seconds");
            fail("Expected pop-up did not appear: " + e.getMessage());
        }
    }

    @Then("user mendapatkan {string} seusai dengan test case vertical bar")
    public void userMendapatkanSeusaiDenganTestCaseTCT(String expectedResult) {
        WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        try {
            // Wait for the pop-up or error message to appear
            WebElement popUpElement = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'error-popup') or contains(@class, 'error-message')]")));

            // Get the actual error message
            String actualResult = popUpElement.getText().trim();
            logger.info("Expected result: " + expectedResult);
            logger.info("Actual result: " + actualResult);

            // Validate the actual result against the expected result
            assertEquals("The pop-up message does not match the expected result", expectedResult, actualResult);
        } catch (Exception e) {
            // Fail the test if the pop-up does not appear within 3 seconds
            logger.severe("Pop-up or error message did not appear within 3 seconds");
            fail("Expected pop-up did not appear: " + e.getMessage());
        }

    }

    @When("ketikan email invalid {string}, pada field email untuk case vertical bar")
    public void ketikanEmailInvalidPadaFieldEmailUntukCaseVerticalBar(String testCase2) throws InterruptedException {
        loadConfig();
        String propertyKey = convertToPropertyKey2(testCase2);
        String invalidEmail = configLoader.getProperty(propertyKey);
        if (invalidEmail == null || invalidEmail.isEmpty()) {
            throw new IllegalArgumentException("Invalid email not found in config.properties for key: " + propertyKey);
        }

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.clear();
        Thread.sleep(1000);
        emailField.sendKeys(invalidEmail);
        logger.info("Typed invalid email: " + invalidEmail + " for test case: " + testCase2);
    }

    private String convertToPropertyKey2(String testCase2) {
        // Mapping of test case descriptions to property keys
        Map<String, String> keyMapping = new HashMap<>();
        keyMapping.put("vertical bar",
                "vertical.bar");

        // Return the corresponding property key or throw an exception if not found
        String propertyKey = keyMapping.get(testCase2);
        if (propertyKey == null) {
            throw new IllegalArgumentException("Test case not found in mapping: " + testCase2);
        }
        return propertyKey;
    }

    @When("ketikan email invalid angka {string}, pada field email untuk case angka")
    public void ketikanEmailInvalidAngkaPadaFieldEmailUntukCaseAngka(String angka) throws InterruptedException {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.clear();
        Thread.sleep(1000);
        emailField.sendKeys(angka);
    }

    @When("Masukkan email valid data, pada field Email")
    public void masukkanEmailValidDataPadaFieldEmail(){
        loadConfig();
        String invalidEmail = configLoader.getProperty("user.email");
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.sendKeys(invalidEmail);
    }

    @When("Klik teks link Forgot Password?")
    public void klikTeksLinkForgotPassword() throws InterruptedException {
        WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(.,'Forgot password?')]")
        ));
        forgotPassword.click();
        Thread.sleep(2000);
    }

    @Then("Ketika Email yang invalid {string} pada field Email")
    public void ketikaEmailYangInvalidPadaFieldEmail(String data) throws InterruptedException {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        emailField.clear();
        Thread.sleep(1000);
        emailField.sendKeys(data);

    }

    @Then("Ketika Email yang valid [data] pada field Email")
    public void ketikaEmailYangValidDataPadaFieldEmail() {
        loadConfig();
        String invalidEmail = configLoader.getProperty("user.email");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_email']")));
        passwordField.sendKeys(invalidEmail);
    }

    @And("Klik button Send Password Reset Link")
    public void klikButtonSendPasswordResetLink() throws InterruptedException {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='commit']")));
        passwordField.click();
        Thread.sleep(2000);
    }

    @When("Klik link Privacy Policy")
    public void klikLinkPrivacyPolicy() throws InterruptedException {
        WebElement klikLinkPrivacyPolicyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='privacy policy']")));
        klikLinkPrivacyPolicyText.click();
        Thread.sleep(2000);
    }

    @When("Klik link Terms of Use")
    public void klikLinkTermsOfUse() throws InterruptedException {
        WebElement klikLinkTermsOfUseText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='terms of use']")));
        klikLinkTermsOfUseText.click();
        Thread.sleep(2000);
    }

    @When("Klik link Code of Conduct")
    public void klikLinkCodeOfConduct() throws InterruptedException {
        WebElement klikLinkCodeOfConductText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='code of conduct']")));
        klikLinkCodeOfConductText.click();
        Thread.sleep(2000);
    }
}




