package com.stas.osikov.paxful.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.naming.NamingException;

public class BrowserFactory {

    public static void setUp(@NonNull final Browser.Browsers type) throws NamingException {
        for (Browser.Browsers t : Browser.Browsers.values()) {
            if (t == type) {
                setWebDriver(t);
                return;
            }
        }
        throw new NamingException("browser name wrong" + ":\nchrome\nfirefox");
    }

    /**
     * Set web driver.
     *
     * @param type the type
     *
     * @throws NamingException the naming exception
     */
    @SneakyThrows(NamingException.class)
    private static void setWebDriver(@NonNull final Browser.Browsers type) {
        Configuration.headless = Browser.IS_HEADLESS;
        switch (type) {
            case CHROME:
                Configuration.browser = WebDriverRunner.CHROME;
                break;
            case FIREFOX:
                Configuration.browser = WebDriverRunner.FIREFOX;
                break;
            default:
                System.out.println(String.format("WebDriver %s not created", type.name()));
                throw new NamingException("browser name wrong" + ":\nchrome\nfirefox");
        }
    }
}
