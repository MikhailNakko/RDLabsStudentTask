Narrative:
As a user
I want to make sure that all functionality on Work Shifts page as expected

Lifecycle:
Before:
Given I am on the login page of application
And I login to application with username 'admin' and password 'admin123'
And I go to Work Shifts page

!-- https://jbehave.org/reference/latest/parameter-converters.html

!-- implement this scenario
Scenario: AC-1 Check that by default General and Twilight work shifts types are shown on work shifts page
Meta: @regression
Then I see General in the Workshift column
And I see Twilight in the Workshift column
!-- implement this scenario
Scenario: AC-2 Check that Work Shift field on Add work shift model requiired
Meta: @regression
When I click on Add Work Shift button
And I try saving new work shift
Then I see Required warning under Work Shift field
!-- implement this scenario
Scenario: AC-3 Check that value in Hours Per Day field calculated propertly
Meta: @debug @regression
When I click on Add Work Shift button
And I set working shift hours from 10:50 to 18:20
Then the Hours per Day field shows 7.50
When I set working shift hours from 8:05 to 20:25
Then the Hours per Day field shows 12.33



