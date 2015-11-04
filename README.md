# chatty

A chat server written using Scala and Akka. Currently a work in progress and it can be compiled and test can be executed.

Steps

- clone repository
- make sure you have sbt
- run sbt in root directory
- execute test task to run the tests
- Run service.Application to start a server on localhost:8080
- Post to localhost:8080/connection with parameter username and room, both strings, to create a connection
