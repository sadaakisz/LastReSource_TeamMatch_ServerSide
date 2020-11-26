Feature: To save the player with player details

  Scenario: client makes call to POST /api/players/ to save the player
    Given the player with player name "Diego" and an id
    When the client calls "/players" with the given details
    Then the client receives status code of 200
    And the response contains player name "Diego"
