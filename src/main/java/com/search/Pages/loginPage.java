package com.search.Pages;

import com.search.drivers.CapturingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends CapturingDriver {

    @FindBy(id="vrm-input")
    private WebElement regInputField;

    @FindBy(className="jsx-3655351943")
    private WebElement freeCarCheckButton;

    @FindBy(xpath="/html/body/div[1]/div/div/div[1]/div/div/dl/div/h5/span")
    private WebElement vehicleNotFound;

    public void enterRegName(String regNum){
        this.regInputField.clear();
        this.regInputField.sendKeys(regNum);
    }

    public void clickFreeCarCheckButton(){

        this.freeCarCheckButton.click();
    }

    public void clickBackButton(){

        this.driver.navigate().back();
    }

    public String vehicleNotFound(){

        return this.vehicleNotFound.getText();
    }

    public loginPage(WebDriver driver) {
        super(driver);
    }

}
