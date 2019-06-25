package com.stas.osikov.paxful.stepDefinition;

import com.stas.osikov.paxful.webdriver.Browser;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class Hooks {
    private TestNGCucumberRunner testNGCucumberRunner;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional(value = "true") String browserName) {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        getBrowser(browserName);
    }

    @SuppressWarnings("all")
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    private void getBrowser(String browser) {
        Browser.getInstance(browser);
    }

    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            makeScreenshot(scenario.getName());
            getWebDriver().quit();
        }
    }

    @Attachment(value = "Page screenshot - {0}", type = "image/png")
    private byte[] makeScreenshot(String fileName) {
        return (((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES));
    }
}
