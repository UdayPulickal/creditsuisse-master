Feature: File Upload
  Scenario: User gives path
    Given User gives wrong path
    When File will not be uploaded
    Then Db operations fail