@Test
Feature: Country Information

  Background:
  Given the base URL and API Key are configured

  Scenario: Get US country information
    When make a GET request to "US"
    Then "US" get the status code 200

  Scenario: Get DE country information
    When make a GET request to "DE"
    Then "DE" get the status code 200

  Scenario: Get GB country information
    When make a GET request to "GB"
    Then "GB" get the status code 200

  Scenario: Get information from non existent country
    When make a GET request to "ABC"
    Then "ABC" get the status code 404

  Scenario: Post new country
    When make a POST request inserting "<name>", "<alpha2_code>" and "<alpha3_code>"
    Then the POST request get the status code 201