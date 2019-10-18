# code-challenge
This is a sample project that has the following characteristics:
* It is generates random data and push it to kafka broker to be consumed and processed by other modules/services.
* It is build almost as complete separated micro-services.
* Implemented using Java as programming language, Spring boot as development framework.
* It is a gradle project and the services are implemented as gradle sub-projects (modules).
* The project modules are completely containerized with Docker

# Project Design
1- data-generator : it is a simple micro-service that generates random temperature readings a long with random GPS coordinates values and pushed to Kafka Brokers.<br/>
2- data-processor : this service keeps listening to kafka brokers, when a new data point is detected is is processed and saved to mongo db.<br/>
3- web : this is a simple http client that exposes the data through rest api which is protected by a basic spring security "In-memory" authentication.<br/>
4- common: this module contains the common objects and utility classes that are used across all other modules.

# How to Run/Deploy
As mentioned before this project is using gradle for building and docker for containerizing the deployments, kindly find below how to install and run on your host.

* Pre-requisites <br />
1- Gradle 5.2 or later must be installed on the host machine.<br/>
2- Docker must be installed on the host machine.<br/>
3- Also the project is on github so you need to be able to checkout through command line or using your IDE.

* How to run <br/>
1- Check out the project to you host machine.<br/>
2- Open a command line and navigate to the directory where you checked out the project.<br/>
3- Run the command : "gradle build" .<br/>
4- Run the command : "docker-compose -f stack.yml up -d" .<br/>
5- After 1-2 minutes all the services should be up and running, you can check using the following command: <br/>
"docker ps" .<br/> 
if the project is running properly there should be 5 containers up and running.

# Check the data through the http client <br/>
* Open any browser.
* Hit the url http://localhost:8080 (hint: make sure the port 8080 is not occupied when you start the deployment)
* You will be prompted to enter a username and password, use the following : <br/>
  user: emil_user  <br/>
  pass: secret  <br/>
  
* Once you are successfully logged in you can see the json data coming in the browser. <br/>
manually refresh the browser every 5 seconds to see the new data.

# Miscellaneous 
* unit testing + integration testing is provided for some of the modules major functionalites, then can be found under the test packages for each module.
* Only not-tested major part is integration testing with kafka broker, would be a bit complex and time consuming for the assignment scope.
* For the documentation part it is provided on-need basis as I believe that the code should be pretty much self explanatory.
* Different debug, error, info messages are provided as needed. 


