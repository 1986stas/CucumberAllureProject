package com.stas.osikov.paxful;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;

@SuppressWarnings("all")
public class DynamicPage extends BasePage {

    private SelenideElement pageTitle = $(By.xpath("//div[@id = 'content']/descendant-or-self::h3"));
    private SelenideElement buttonStart = $(By.xpath("//button[contains (text(), 'Start')]"));
    private SelenideElement finishText = $(By.xpath("//*[@id = 'finish']/*"));

    private static By PROGRESS_BAR = By.xpath("//*[@id = 'loading']/*");

    @Step
    public DynamicPage openDynamicPage(){
        openPage("/dynamic_loading", FramePage.class);
        waitForPageLoad();
        return this;
    }

    @Step
    public String getPageTitleText(){
        return readText(pageTitle);
    }

    @Step
    public DynamicPage clickOnLink(String linkNumber){
        $(By.xpath("//div[@id = 'content']/descendant-or-self::a[contains (text(), '$1')]".
                replace("$1", linkNumber))).scrollIntoView(true).click();
        return this;
    }

    @Step
    public boolean isStartButtonDisplyaed(){
        return buttonStart.isDisplayed();
    }

    @Step
    public DynamicPage clickOnStartButton(){
        buttonStart.click();
        return this;
    }

    @Step
    public String getDynamicText(){
        waitForAnEelementIsDisappeared();
        waitFor(By.xpath("//*[@id = 'finish']/*"));
        return readText(finishText);
    }
    
    private void waitForAnEelementIsDisappeared(){
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id = 'finish']/*")));
    }

    @Step
    public DynamicPage clickOnBackButton(){
        Selenide.back();
        return this;
    }
}
