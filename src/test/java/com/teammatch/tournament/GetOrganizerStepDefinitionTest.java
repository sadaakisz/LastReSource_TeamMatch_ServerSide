package com.teammatch.tournament;

import com.teammatch.tournament.TournamentApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
@ContextConfiguration(classes = TournamentApplicationTests.class)
public class GetOrganizerStepDefinitionTest {


    @Given("the organizer saved with organizer name {string} and an id")
    public void theOrganizerSavedWithOrganizerNameAndAnId(String arg0) throws Exception {
        //
    }

    @When("the client calls GET {string} with an organizer id")
    public void theClientCallsGETWithAnOrganizerId(String arg0) throws Exception {
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int arg0) throws Exception {
    }

    @And("the response contains organizer name {string}")
    public void theResponseContainsOrganizerName(String arg0) throws Exception {
    }
}
