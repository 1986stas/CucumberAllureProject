package com.stas.osikov.paxful;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.stas.osikov.paxful.webdriver.Browser;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

abstract class BasePage<Page extends BasePage> {
    private static String BASE_URL = Browser.BROWSER_URL;
    private static final int TIME_TO_WAIT = Browser.TIMEOUT_FOR_CONDITION;


    static void waitForPageLoad() {
        new WebDriverWait(Browser.getDriver(), Browser.PAGE_WAIT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Synchronized
    void openPage(String pageUrl, Class<Page> pageClass){
        open(BASE_URL + pageUrl, pageClass);
    }

    /**
     * methods below can be improved by switching between By or WebElement, or any other Remote WebElement
     * @param elementAttr
     * @param <V>
     */
    <V> String readText(V elementAttr){
        return  ((SelenideElement) elementAttr).scrollIntoView(true).waitUntil(Condition.visible, TIME_TO_WAIT).text();
    }

    <V> void writeText(V elementAttr, String text){
        ((SelenideElement) elementAttr).scrollIntoView(true).waitUntil(Condition.visible, TIME_TO_WAIT).setValue(text);
    }

    /**
     * Wait for selenide element.
     * @param elementLocator the element locator
     * @return the selenide element
     */
    public static SelenideElement waitFor(By elementLocator) {
        return $(elementLocator).shouldBe(Condition.exist).waitUntil(Condition.visible, TIME_TO_WAIT);
    }

    @SneakyThrows(TimeoutException.class)
    <V> V waitFor(Function<? super WebDriver, V> condition) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        FluentWait<WebDriver> wait = new FluentWait<>(Browser.getDriver()).withTimeout(Browser.IMPLICITY_WAIT, timeUnit)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class).
                        pollingEvery(1000, TimeUnit.MILLISECONDS);
        return wait.until(condition);

    }
}
