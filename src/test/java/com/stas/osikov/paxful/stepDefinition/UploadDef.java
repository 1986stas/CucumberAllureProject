package com.stas.osikov.paxful.stepDefinition;

import com.stas.osikov.paxful.UploadPage;
import cucumber.api.java8.En;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class UploadDef implements En {
    private UploadPage uploadPage = new UploadPage();

    public UploadDef() {
        Given("^Open upload page$", () -> {
            uploadPage.openUploadPage();
        });
        When("^Upload button is displayed$", () -> {
            assertTrue(uploadPage.isUploadButtonDisplayed());
        });
        And("Upload file {string}", (String file) -> {
            uploadPage.uploadFile(file);
        });
        And("^Click on upload button$", () -> {
            uploadPage.clickOnUploadButton();
        });

        Then("{string} title is displayed", (String title) -> {
            assertThat("Title is not presented", uploadPage.getTextSuccessUpload(), is(equalTo(title)));
        });

        Then("File is uploaded {string}", (String file) -> {
            uploadPage.isFileUploaded(file);
        });

    }
}
