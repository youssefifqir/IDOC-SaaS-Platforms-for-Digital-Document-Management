@ignore
Feature: common routine that does not update reference

  Background:
    * def uuid = function() { return '' + java.util.UUID.randomUUID(); }
    * def DbUtils = Java.type('ma.peps.sqli.core.util.DbUtils')

  Scenario: Setting Global variables
    * def uniqueId = uuid()
    * def db = new DbUtils(datasource)

#    * url rootUrl + 'login'
#    * request adminCredentials
#    * method POST
#    * status 200
#    * match response.tokenType == "Bearer"
#    * match response.roles contains 'ROLE_ADMIN'
#    * def adminToken = response.accessToken
    * def adminToken = ""
