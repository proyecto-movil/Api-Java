Feature: show subscription details
  Scenario: client sees subscription details
    Given the client is on the main page
    When the client searches for a subscription 1
    Then the client receives subscription 1 details