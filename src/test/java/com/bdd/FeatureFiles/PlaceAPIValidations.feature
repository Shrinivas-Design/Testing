Feature: Validation of Google Place API's
@AddPlace
Scenario Outline: Add,Delete Operations of Google Place API

Given combine request payload and query Parameter with "<name>" "<language>" "<address>"

When user call "AddPlaceAPI" with "post" http request

Then verify that getting response code as '200'

And veriy that "status" as "OK"
And veriy that "scope" as "APP"
And verify placeId created to "<name>" using "GetPlaceAPI"


Examples:

|name   | language  | address         | 
|AAHome | English   | Sea cross center|  
#|BBHome | French    | central mall    |

@DeletePlace
Scenario: Rest API test for delete API

Given Request the Payload for delete API

When user call "DeletePlaceAPI" with "post" http request

Then verify that getting response code as '200'