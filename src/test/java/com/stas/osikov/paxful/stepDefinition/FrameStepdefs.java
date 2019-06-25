package com.stas.osikov.paxful.stepDefinition;

import com.stas.osikov.paxful.FramePage;
import cucumber.api.java8.En;

public class FrameStepdefs implements En {

    private FramePage framePage = new FramePage();

    public FrameStepdefs() {
        Given("^Open frame page$", () -> {
            framePage.openFramePage();
        });

        When("Frame contains text {string}", (String value) -> {
            framePage.frameShouldHaveExactText(value);
        });

    }
}
