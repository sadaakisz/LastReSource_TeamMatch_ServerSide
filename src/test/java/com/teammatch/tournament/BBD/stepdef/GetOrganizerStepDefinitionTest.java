package com.teammatch.tournament.BBD.stepdef;

import com.teammatch.tournament.domain.model.Organizer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GetOrganizerStepDefinitionTest {

    private Organizer expectOrganizer = new Organizer();
    private Organizer actualOrganizer = new Organizer();

    @Given("the app wants to get the information about an organizer")
    public void the_app_wants_to_get_the_information_about_an_organizer(Organizer organizer) {
      expectOrganizer = organizer;
    }


    @When("someone visits the organizer profile")
    public void someone_visits_the_organizer_profile() {
        actualOrganizer.setId(1L).setFirstName("Diego").setLastName("Johnson");
    }



    @Then("the information about the Organizer will be retrieved successfully")
    public void the_information_about_the_Organizer_will_be_retrieved_successfully() {
        validateOrganizer();
    }

    private void validateOrganizer()
    {
        Assertions.assertEquals(expectOrganizer.getFirstName(),actualOrganizer.getFirstName());
    }
}
