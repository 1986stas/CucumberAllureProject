Feature: In this tiny feature we are going to check possibility to upload file,
  wait for an element, switch between frames

  @smoke
  Scenario: In this scenario I'm checking frames content
    Given Open frame page
    When Frame contains text "BOTTOM"
    When Frame contains text "RIGHT"
    When Frame contains text "LEFT"
    When Frame contains text "MIDDLE"

  @smoke
  Scenario: In this scenario I'm checking text appearance
    Given Open dynamic loading page
    When "Dynamically Loaded Page Elements" title contains right text
    When Click on "1" link
    And Start button is displayed
    And Click on start button
    Then "Hello World!" is displayed
    Then Click on back button
    When Click on "2" link
    And Start button is displayed
    And Click on start button
    Then "Hello World!" is displayed

  @smoke
  Scenario: In this scenario I'm checking file upload process
    Given Open upload page
    When Upload button is displayed
    And Upload file "images.jpeg"
    And Click on upload button
    Then "File Uploaded!" title is displayed
    Then File is uploaded "images.jpeg"

