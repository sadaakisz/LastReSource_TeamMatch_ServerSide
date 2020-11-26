package com.teammatch.tournament;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetPlayerStepDefinitionTest {

    @Given("the player saved with player name {string} and an id")
    public void thePlayerSavedWithPlayerNameAndAnId(String arg0) throws Exception {
    }

    @When("the client calls GET {string} with an player id")
    public void theClientCallsGETWithAnPlayerId(String arg0) throws Exception {
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int arg0) throws Exception {
    }

    @And("the response contains player name {string}")
    public void theResponseContainsPlayerName(String arg0) throws Exception {
    }
}
