package step_definitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    public static WebDriver webDriver;

    @Before
    public void openBrowser(){

        // Membaca konfigurasi dari config.properties
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/features/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String appUrl = properties.getProperty("app.url");
        String windowSize = properties.getProperty("browser.window.size");

        ChromeOptions a = new ChromeOptions();
        a.addArguments("--remote-allow-origins=*");
        a.addArguments("--headless");
        a.addArguments("--disable-gpu");
        a.addArguments("--incognito");
        a.addArguments("--no-sandbox");
        a.addArguments("--disable-dev-shm-usage");
        a.addArguments("--window-size=" + windowSize);
        a.addArguments("--disable-extensions");
        a.addArguments("--disable-logging");

        WebDriverManager.chromedriver().setup();

        webDriver = new ChromeDriver(a);
        webDriver.get(appUrl);
        webDriver.manage().window().maximize();
    }

    @After
    public void closeBrowser(){
        webDriver.quit();
    }

}