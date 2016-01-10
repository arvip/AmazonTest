Feature: Test search and sort feature in Amazon
   In order to verify that search and sort for 'Nikon' results in correct order
   As a user of Amazon
   I want to verify if 'Nikon D3X' appears with search and sort: high to low price

   Scenario Outline: Test Search and Sort for Nikon
   Given I launch "<AmazonURL>"
   When I enter Nikon in search and sort by price High to Low
   When I select second product and click for details
   Then I should verify if the product topic contains Nikon D3X
   
   Examples:
   |AmazonURL				|
   |http://www.amazon.com/	|