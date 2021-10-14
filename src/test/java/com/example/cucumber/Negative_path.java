package com.example.cucumber;

import com.credisuisstest.Fileread;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class Negative_path {
    Fileread fl;

    @Given("User gives wrong path")
    public void userGivesWrongPath() throws Exception{
        try {
            Fileread fl = new Fileread("/E:/Credit/creditsuisse-master/logfile.word");

        fl.filereadmethod();
        if (fl.filevalidation())
        {
            System.out.println("Now file path will be called");
        }

        }

        catch (Exception e)
        {
            System.out.println("Failed");

        }


    }


    @When("File will not be uploaded")
    public void fileWillNotBeUploaded() {
        System.out.println("User gives wrong path");
    }

    @Then("Db operations fail")
    public void dbOperationsFail() {
        try {
            if (!fl.dbvalidation()) {
                System.out.println("Records will be inserted into database");
            }
        }
        catch (Exception e)
        {
            System.out.println("File upload to Db failed");
        }
    }
}
