package com.search.drivers;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class CapturingDriver {
    protected WebDriver driver;

    public CapturingDriver(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}


