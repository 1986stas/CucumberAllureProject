package com.stas.osikov.paxful.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.stas.osikov.paxful.settings.IDriverEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

public class Browser {

    private final static IDriverEnvironment driverEnv = ConfigFactory.create(IDriverEnvironment.class);

    private static Browsers currentBrowser = Browsers.valueOf((System.getenv("browser_name") == null
            ? driverEnv.browser()
            : System.getenv("browser_name")).toUpperCase());
    private static Browser instance;
    static final boolean IS_HEADLESS = driverEnv.browserHeadless();
    public static final int TIMEOUT_FOR_CONDITION = driverEnv.defaultConditionTimeout();
    public static final int IMPLICITY_WAIT = driverEnv.implicityWait();
    public static final int PAGE_WAIT = driverEnv.pageImplicityWait();
    public static final String BROWSER_URL = driverEnv.baseUrl();
    private static final String CHROME_VERSION =driverEnv.browserVersion();
    private static final String BROWSER_SIZE = driverEnv.browserSize();

    private Browser(){
        System.out.println(String.format("browser %s is ready", currentBrowser.name()));
    }

    public static void getInstance(String browser) {
        if (instance == null) {
            synchronized (Browser.class) {
                if (!Boolean.valueOf(browser)) {
                    currentBrowser = Browsers.valueOf(browser.toUpperCase());
                }
                initNewDriver();
                instance = new Browser();
            }
        }
    }

    public static WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    private static void initNewDriver() {
        Configuration.timeout = PAGE_WAIT;
        WebDriverManager.chromedriver().version(CHROME_VERSION);
        Configuration.pollingInterval = 300;
        Configuration.browserSize = BROWSER_SIZE;
        try {
            BrowserFactory.setUp(currentBrowser);
        } catch (NamingException e) {
            System.out.println("Browser: getting New Driver" + e.getMessage());
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome");

        @Getter
        private final String value;
    }
}
