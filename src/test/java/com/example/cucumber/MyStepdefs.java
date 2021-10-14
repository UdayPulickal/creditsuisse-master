package com.example.cucumber;

import com.credisuisstest.Creditsuissetest;
import com.credisuisstest.Fileread;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    Fileread fl=new Fileread("/E:/Credit/creditsuisse-master/logfile.json");


    @Given("User gives path")
    public void userGivesPath() {
        fl.filereadmethod();
        if (fl.filevalidation())
        {

            System.out.println("Now file path will be called");
        }


    }

    @When("user goes on that path")
    public void userGoesOnThatPath() {
        System.out.println("Data will be read and processed");
    }

    @Then("file uploades")
    public void fileUploades() throws Exception {
        fl.dboperations();
        if (fl.dbvalidation()) {
            System.out.println("Records will be inserted into database");
        }


    }
}
