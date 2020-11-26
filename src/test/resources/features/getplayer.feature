Feature: To retrieve the player with player details

  Scenario: retrieve the player with player id
    Given the player saved with player name "Diego" and an id
    When the client calls GET "/players/{organizerId}" with an player id
    Then the client receives status code of 200
    And the response contains player name "Diego"