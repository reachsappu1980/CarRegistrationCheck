Feature: Free Car Registration check
    As a user I want to verify the car registration number
    So that I can make have all the details about the car before purchase

Background:
    Given I setup a firefox browser with below url
    | url                       |
    |https://cartaxcheck.co.uk/ |

Scenario: Verify car registration details for the give registration numbers
    Given I have a extracted car registration numbers from input file
    And I extract all the valid registrations number from output file
    When I perform a free car check for each registration number
    Then I compare the full car registration details with output file
