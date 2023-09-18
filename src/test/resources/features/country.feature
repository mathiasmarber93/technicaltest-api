@API
Feature: Country Information

  Background:
    Given the base URL is configured in serenity.properties
    And the API key is configured in serenity.properties

  Scenario: Get US country information
    When I request information for country "US"
    Then the response code should be 200

  Scenario: Get DE country information
    When I request information for country "DE"
    Then the response code should be 200

  Scenario: Get GB country information
    When I request information for country "GB"
    Then the response code should be 200

  Scenario: Get information from non existent country
    When I request information for country "ABC"
    Then the response code should be 404

  Scenario: Post new country
    When I send a POST request to create a new country
    Then the response code should be 201