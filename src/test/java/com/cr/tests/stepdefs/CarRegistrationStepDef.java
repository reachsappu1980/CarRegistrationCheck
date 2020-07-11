package com.cr.tests.stepdefs;

import com.search.Pages.freeCarCheckPage;
import com.search.Pages.loginPage;
import com.search.util.CSVFileReaderUtil;
import com.search.util.RegRecord;
import com.search.util.TextSearchUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CarRegistrationStepDef {

    public WebDriver driver;
    CSVFileReaderUtil reader;
    List<RegRecord> regRecordsFromOutput;
    List<String> foundPlates;
    List<RegRecord> carTaxCheckRecords;


    private static final String NUMBER_PLATE_PATTERN = "[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}";


    @Given("^I setup a firefox browser with below url$")
    public void i_setup_a_firefox_browser_with_below_url(List<String> url) throws Exception {


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("marionette", true);


        // Initialize WebDriver
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url.get(1));

    }


    @Given("^I have a extracted car registration numbers from input file$")
    public void i_have_a_extracted_car_registration_numbers_from_input_file() throws Exception {

        TextSearchUtil searchUtil = new TextSearchUtil();
        foundPlates = searchUtil.searchString(NUMBER_PLATE_PATTERN, getFileAsInputStream("car_input.txt"));
        Assert.assertTrue(!foundPlates.isEmpty());

    }

    @Given("^I extract all the valid registrations number from output file$")
    public void i_extract_all_the_valid_registrations_number_from_output_file() throws Exception {

        reader = new CSVFileReaderUtil();
        regRecordsFromOutput = reader.getRegRecords(getFileAsInputStream("car_output.txt"));
        Assert.assertNotNull(regRecordsFromOutput);
        Assert.assertTrue("No of records read 4", regRecordsFromOutput.size() == 4);

    }

    @When("^I perform a free car check for each registration number$")
    public void i_perform_a_free_car_check_for_each_registration_number() throws Exception {


        carTaxCheckRecords = new ArrayList<>();

        loginPage loginPage = new loginPage(driver);

        for (String plate : foundPlates) {
            loginPage.enterRegName(plate);
            loginPage.clickFreeCarCheckButton();
            freeCarCheckPage freeCarCheckPage = new freeCarCheckPage(driver);
            if(!freeCarCheckPage.getRegName().isEmpty()) {
                carTaxCheckRecords.add(new RegRecord(freeCarCheckPage.getRegName(), freeCarCheckPage.getRegMake(), freeCarCheckPage.getRegModel(), freeCarCheckPage.getRegColour(), freeCarCheckPage.getRegYear()));

            }
            loginPage.clickBackButton();
        }
    }

    @Then("^I compare the full car registration details with output file$")
    public void i_compare_the_full_car_registration_details_with_output_file() throws Exception {


        for (RegRecord carTaxCheckRecord : carTaxCheckRecords) {

            RegRecord actualRegRecord = getRegRecordFromOutputList(carTaxCheckRecord.getRegistration());

            Assert.assertTrue("Registartion number matched", actualRegRecord.getRegistration().equals(carTaxCheckRecord.getRegistration()));
            Assert.assertTrue("Vechile make matched", actualRegRecord.getMake().equals(carTaxCheckRecord.getMake()));
            Assert.assertTrue("Vechile model matched", actualRegRecord.getModel().equals(carTaxCheckRecord.getModel()));
            Assert.assertTrue("vechile colour matched", actualRegRecord.getColor().equals(carTaxCheckRecord.getColor()));
            Assert.assertTrue("Vechile year matched", actualRegRecord.getYear().equals(carTaxCheckRecord.getYear()));

        }


    }

    public InputStream getFileAsInputStream(String filePath) {
        return getClass().getClassLoader().getResourceAsStream(filePath);
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    public RegRecord getRegRecordFromOutputList(String registration) {
        for (RegRecord regRecord : regRecordsFromOutput) {
            if (regRecord.getRegistration().equalsIgnoreCase(registration))
                return regRecord;


        }
        return null;
    }


}
