Feature: show subscription details
  Scenario: client sees subscription details
    Given the client is on the main page
    When the client searches for a subscription 1
    Then the client recieves subscription 1 details