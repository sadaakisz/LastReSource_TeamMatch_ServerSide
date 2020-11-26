Feature: To retrieve the organizer with organizer details

  Scenario: retrieve the organizer with organizer id
    Given the organizer saved with organizer name "Luis" and an id
    When the client calls GET "/organizers/{organizerId}" with an organizer id
    Then the client receives status code of 200
    And the response contains organizer name "Luis"