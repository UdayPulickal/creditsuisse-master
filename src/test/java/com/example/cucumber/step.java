//package com.example.cucumber;
//
//import com.credisuisstest.Fileread;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import junit.framework.Assert;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class step {
//
//
//    @Before
//    public void beforeScenario(){
//        System.out.println("This will run before the Scenario");
//    }
//    Fileread fl=new Fileread("/E:/Credit/creditsuisse-master/logfile.json");
//
//    @Given("User goes to the file path")
//    public void userGoesToTheFilePath() {
//        fl.toString();
//    }
//
//    @When("User reads json file")
//    public void Userreadsjsonfile() {
//        fl.toString();
//    }
//
//    @Then("file should get uploaded")
//    public void fileShouldGetUploaded() throws Exception {
//
//        assertEquals("Filename",123);
//    }
//
//    @When("User provides word file path")
//    public void userProvidesWordFilePath() {
//    }
//
//    @Then("file should not get uploaded")
//    public void fileShouldNotGetUploaded() {
//    }
//    @After
//    public void afterScenario(){
//        System.out.println("This will run after the Scenario");
//    }
//}
