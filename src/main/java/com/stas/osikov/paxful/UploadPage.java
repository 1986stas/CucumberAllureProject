package com.stas.osikov.paxful;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;

@SuppressWarnings("all")
public class UploadPage extends BasePage {

    private SelenideElement uploadButton = $(By.xpath("//input[@class = 'button']"));

    @Step
    public UploadPage openUploadPage(){
        openPage("/upload", UploadPage.class);
        waitForPageLoad();
        return this;
    }

    @Step
    public boolean isUploadButtonDisplayed(){
        return uploadButton.isDisplayed();
    }

    @Step
    public UploadPage uploadFile(String fileToUpload){
        File file = new File("src/test/resources/attachment/" + fileToUpload);
        $(By.xpath("//input[@id = 'file-upload']")).uploadFile(file);
        return this;
    }

    @Step
    public UploadPage clickOnUploadButton(){
        uploadButton.click();
        return this;
    }

    @Step
    public boolean isFileUploaded(String file){
        return $(By.xpath("//div[@id = 'uploaded-files']")).text().equals(file);
    }

    @Step
    public String getTextSuccessUpload(){
        return readText($(By.xpath("//div[@class = 'example']/h3")));
    }
}
