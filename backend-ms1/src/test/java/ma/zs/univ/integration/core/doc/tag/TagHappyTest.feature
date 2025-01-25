Feature: Tag

  Background:
    * call read('karate-config.js')
    * call read('db_cleaner.js')
    * url tagUrl
    * header Content-Type = 'application/json'

    * def postBody = read('TagSave.json')
    * def objectSchema = read('TagSchema.json')
    * def uuid = function() { return '' + java.util.UUID.randomUUID(); }
    * postBody.code = uuid()

  @save
  Scenario: POST item and GET it by ID
    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * request postBody
    * method POST
    * status 201


    * path 'id', response.id
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 404
    * karate.match("each response contains objectSchema")


  @put
  Scenario: Update item and GET it by ID
    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * request postBody
    * method POST
    * status 201


    * path 'id', response.id
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 200
    * karate.match("each response contains objectSchema")


  @delete
  Scenario: DELETE item and GET it

    # POST an item
    * path ''
    * header Authorization = 'Bearer ' + adminToken
    * request postBody
    * method POST
    * status 201
    * def myId = response.id

    # Assert that the item exist
    * path 'id', myId
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 200

    # Delete the item
    * path 'id', myId
    * header Authorization = 'Bearer ' + adminToken
    * method DELETE
    * status 200


    # Assert that the item not exist successfully
    * path 'id', myId
    * header Authorization = 'Bearer ' + adminToken
    * method GET
    * status 404


  @findAll
  Scenario Outline: Find All items (204 & 200)


    * def payload = ("<method>" == "POST") ? postBody : {}
    * postBody.code = uniqueId
    * path <paths>
    * header Authorization = 'Bearer ' + adminToken
    * request payload
    * method <method>
    * status <responseCode>
    * def res = karate.match(<matching>[0])
    * match res == { pass: true, message: null }
    * def res = karate.match(<matching>[1])
    * match res == { pass: true, message: null }


    Examples:
      | responseCode | paths         | method | matching                                                                                 |
      | 204          | ''            | GET    | ["response.length == 0", "payload != ''"]                                                |
      | 201          | ''            | POST   | ["payload != ''", "payload != ''"]                                                       |
      | 200          | ''            | GET    | ["each response contains objectSchema "," response[0].code == postBody.code"] |


