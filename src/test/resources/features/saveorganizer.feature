Feature: To save the organizer with organizer details

  Scenario: client makes call to POST /organizers to save the organizer
    Given the organizer with organizer name "Luis" and an id
    When the client calls "/organizers" with the given details
    Then the client receives status code of 200
    And the response contains organizer name "Luis"
