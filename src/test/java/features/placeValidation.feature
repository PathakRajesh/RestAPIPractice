Feature: Validating Place API

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name         | language | address                               |
      | Test Place   | English  | 123 Test St, Test City, TC 12345      |
   #   | Sample Place | French   | 456 Sample Ave, Sample City, SC 67890 |

  @DeletePlace

  Scenario: Verify if deletePlace API successfully deletes a place
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
