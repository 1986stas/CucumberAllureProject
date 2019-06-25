package com.stas.osikov.paxful;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@SuppressWarnings("all")
public class FramePage extends BasePage {

    private final static String FRAME = "//frame[contains (@name, 'frame-$1')]";
    private final static String BODY_TEXT = "//*[contains (text(), '$1')]";

    @Step
    public FramePage openFramePage(){
        openPage("/nested_frames", FramePage.class);
        return this;
    }

    @Step
    public FramePage frameShouldHaveExactText(String value){
        SelenideElement actualText = $(By.xpath(BODY_TEXT.replace("$1", value)));
        switch (value){
            case "BOTTOM":{
                Selenide.switchTo().frame(1);
                actualText.shouldHave(Condition.text(value));
                break;
            }
            case "RIGHT":{
                switchToTop(actualText, value);
                break;
            }
            case "LEFT":{
                switchToTop(actualText, value);
                break;
            }
            case "MIDDLE":{
                switchToTop(actualText, value);
                break;
            }
            default: throw new NoSuchElementException("No element has been found");
        }
        return this;
    }

    private void switchToTop(SelenideElement frameText, String value){
        switchTo().defaultContent();
        Selenide.switchTo().frame(0);
        Selenide.switchTo().frame($(By.xpath(FRAME.replace("$1", value.toLowerCase()))));
        frameText.shouldHave(Condition.exactText(value));
    }
}
