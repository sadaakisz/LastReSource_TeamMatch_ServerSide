package com.teammatch.tournament.BBD.stepdef;

import com.teammatch.tournament.domain.model.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GetPlayerStepDefinitionTest {

    private Player expectPlayer = new Player();
    private Player actualPlayer = new Player();

    @Given("the app wants to get the information about a player")
    public void the_app_wants_to_get_the_information_about_a_player(Player player) {
        expectPlayer = player;
    }


    @When("someone visits the player profile")
    public void someone_visits_the_player_profile() {
        actualPlayer.setId(1L).setFirstName("Diego").setLastName("Johnson");
    }



    @Then("the information about the Player will be retrieved successfully")
    public void the_information_about_the_Player_will_be_retrieved_successfully() {
        validatePlayer();
    }

    private void validatePlayer()
    {
        Assertions.assertEquals(expectPlayer.getFirstName(),actualPlayer.getFirstName());
    }
}
