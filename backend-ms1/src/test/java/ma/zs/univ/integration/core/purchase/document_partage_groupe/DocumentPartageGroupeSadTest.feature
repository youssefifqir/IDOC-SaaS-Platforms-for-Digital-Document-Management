Feature: DocumentPartageGroupe

  Background:
    * call read('karate-config.js')
    * call read('db_cleaner.js')
    * url documentPartageGroupeUrl
    * header Content-Type = 'application/json'

    * def postBody = read('DocumentPartageGroupeSave.json')
    * def FindAllSchema = read('DocumentPartageGroupeSchema.json')

  Scenario: Fail - GetByID Not Found

    * path 'id', 99999999
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 404
    * match response.length == 0



  Scenario: Fail - POST DocumentPartageGroupe without Body

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method POST
    * status 400
    * match response.error == "Bad Request"



  Scenario: Fail - POST DocumentPartageGroupe without Authorization

    * path ''
    * header Authorization = 'Bearer unvalid'
    * request postBody
    * method POST
    * status 500



  Scenario: Fail - Save DocumentPartageGroupe with method PATCH

    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * method PATCH
    * status 405
    * match response.error == "Method Not Allowed"
