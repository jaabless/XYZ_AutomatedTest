package org.xyzbank.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.xyzbank.pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static LoginPage loginPage;

    @BeforeEach
//    public static void setupDriver() {
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new"); // 'new' is better for Chrome 109+
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//
//        driver = new ChromeDriver(options);
//        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        loginPage = new LoginPage(driver);
//    }

    public void setupDriver() {
        // Detect OS
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        }

        ChromeOptions options = new ChromeOptions();
        if (!os.contains("win")) {
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }



    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
