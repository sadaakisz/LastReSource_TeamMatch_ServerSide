Feature: Retrieving Organizer


  Scenario: Getting an Organizer
    Given the app wants to get the information about an organizer
      | Id  | FirstName | LastName |
      | 1   |  Diego    | Johnson  |
    When someone visits the organizer profile
    Then the information about the Organizer will be retrieved successfully