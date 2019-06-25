package com.stas.osikov.paxful.stepDefinition;

import com.stas.osikov.paxful.DynamicPage;
import cucumber.api.java8.En;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DynamicStepdefs implements En {

    private DynamicPage dynamicPage = new DynamicPage();

    public DynamicStepdefs() {
        Given("^Open dynamic loading page$", () -> {
            dynamicPage.openDynamicPage();
        });
        When("{string} title contains right text", (String title) -> {
            assertThat(dynamicPage.getPageTitleText()).isEqualTo(title);
        });
        When("Click on {string} link", (String linkNumber) -> {
            dynamicPage.clickOnLink(linkNumber);
        });
        And("^Start button is displayed$", () -> {
            assertThat(dynamicPage.isStartButtonDisplyaed());
        });
        And("^Click on start button$", () -> {
            dynamicPage.clickOnStartButton();
        });
        Then("{string} is displayed", (String text) -> {
            assertThat(dynamicPage.getDynamicText()).isEqualTo(text);
        });
        Then("^Click on back button$", () -> {
            dynamicPage.clickOnBackButton();
        });
    }
}
