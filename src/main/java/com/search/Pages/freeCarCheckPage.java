package com.search.Pages;



import  com.search.drivers.CapturingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class freeCarCheckPage extends CapturingDriver {

    @FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/div/span/div[2]/dl[1]/dd")
    private WebElement regNumber;

    @FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/div/span/div[2]/dl[2]/dd")
    private WebElement make;

    @FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/div/span/div[2]/dl[3]/dd")
    private WebElement model;

    @FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/div/span/div[2]/dl[4]/dd")
    private WebElement colour;

    @FindBy(xpath="/html/body/div[1]/div/div/div[3]/div[1]/div/span/div[2]/dl[5]/dd")
    private WebElement year;

    public freeCarCheckPage(WebDriver driver) {
        super(driver);
    }

    public String getRegName(){
        return this.regNumber.getText();
    }
    public String getRegMake(){
        return this.make.getText();

    }
    public String getRegModel(){
        return this.model.getText();
    }
    public String getRegColour(){
        return this.colour.getText();
    }
    public String getRegYear(){
        return this.year.getText();
    }

}
