package com.stas.osikov.paxful.settings;

import org.aeonbits.owner.Config;

import java.util.concurrent.TimeUnit;

import static com.stas.osikov.paxful.settings.PropertyConstants.DRIVER;

@Config.Sources(value = {"classpath:" + DRIVER})
public interface IDriverEnvironment extends Config {


    @DefaultValue("chrome")
    String browser();

    @DefaultValue("75")
    String browserVersion();

    @DefaultValue("https://the-internet.herokuapp.com")
    String baseUrl();

    @DefaultValue("true")
    Boolean browserHeadless();

    @DefaultValue("5000")
    Integer defaultConditionTimeout();

    @DefaultValue("4000")
    Integer pageImplicityWait();

    @DefaultValue("10000")
    Integer implicityWait();

    @DefaultValue("1920x1080x24")
    String browserSize();
}
