package com.stas.osikov.paxful;

import com.stas.osikov.paxful.stepDefinition.Hooks;
import cucumber.api.CucumberOptions;


@CucumberOptions(plugin = {"pretty", "html:target/cucumber-pretty","json:target/cucumber.json"},
                features = {"src/test/resources/features"},
                glue = "com.stas.osikov.paxful.stepDefinition",
                strict = true,
                tags = "@smoke")
public class CucumberRunner extends Hooks {

}
