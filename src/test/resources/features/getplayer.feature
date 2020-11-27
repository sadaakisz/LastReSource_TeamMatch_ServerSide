Feature: Retrieving Player


  Scenario: Getting a Player
    Given the app wants to get the information about a player
      | Id  | FirstName | LastName |
      | 1   |  Diego    | Johnson  |
    When someone visits the player profile
    Then the information about the Player will be retrieved successfully