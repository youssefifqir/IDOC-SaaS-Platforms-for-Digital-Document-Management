Feature: APP Test

  Background:
    * url actuatorUri
    * header Content-Type = 'application/json'


  Scenario: Check DB, DiscSpace and Ping UP
    * path 'health'
    * method GET
    * status 200


  Scenario: Check App Info
    * path 'info'
    * method GET
    * status 200
    * match response.app.name == "univ"

